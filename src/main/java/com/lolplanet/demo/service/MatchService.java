package com.lolplanet.demo.service;

import com.lolplanet.demo.api.riot.RiotApi;
import com.lolplanet.demo.api.riot.dto.match.MatchReqDto;
import com.lolplanet.demo.api.riot.dto.match.ParticipantReqDto;
import com.lolplanet.demo.domain.match.Match;
import com.lolplanet.demo.domain.match.MatchRepository;
import com.lolplanet.demo.domain.participant.Participant;
import com.lolplanet.demo.domain.participant.ParticipantRepository;
import com.lolplanet.demo.web.dto.MatchResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final ParticipantRepository participantRepository;
    private final RiotApi riotApi;

    public void renew(String puuid, int start, int count) {
        List<String> gameIds = findGameIdList(puuid, start, count);
        gameIds.parallelStream().forEach(this::saveMatchAndParticipant);
    }

    @Transactional
    public Long saveMatchAndParticipant(String matchId) {
        String url = String.format("https://asia.api.riotgames.com/lol/match/v5/matches/%s", matchId);

        try {
            MatchReqDto matchReqDto = riotApi.callApi(url, MatchReqDto.class);

            Match match = matchRepository.save(matchReqDto.toEntity());

            List<ParticipantReqDto> participantReqDtos = matchReqDto.getInfo().getParticipants();

            List<Participant> participants = participantReqDtos.stream()
                    .map(p -> p.toEntity(match))
                    .collect(Collectors.toList());

            participantRepository.saveAll(participants);

            return match.getGameId();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    public Page<MatchResDto> findList(Pageable pageable, String summonerName) {
        List<MatchResDto> dto = matchRepository.findList(pageable, summonerName).stream()
                .map(m -> new MatchResDto(m, summonerName))
                .collect(Collectors.toList());

        return new PageImpl<>(dto, pageable, dto.size());
    }

    public List<String> findGameIdList(String puuid, int start, int count) {
        String url = String.format("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%d&count=%d", puuid, start, count);
        try {
            return  riotApi.callApi(url, List.class);
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }
}
