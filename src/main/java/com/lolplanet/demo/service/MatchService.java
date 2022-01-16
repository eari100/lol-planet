package com.lolplanet.demo.service;

import com.lolplanet.demo.api.riot.RiotApi;
import com.lolplanet.demo.api.riot.dto.match.MatchReqDto;
import com.lolplanet.demo.api.riot.dto.match.ParticipantReqDto;
import com.lolplanet.demo.domain.match.Match;
import com.lolplanet.demo.domain.match.MatchRepository;
import com.lolplanet.demo.domain.participant.Participant;
import com.lolplanet.demo.domain.participant.ParticipantRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public Long save(String matchId) {
        String url = String.format("https://asia.api.riotgames.com/lol/match/v5/matches/%s", matchId);

        try {
            MatchReqDto matchReqDto = riotApi.callApi(url, MatchReqDto.class);

            Match match = matchRepository.save(matchReqDto.toEntity());

            List<ParticipantReqDto> participantReqDtos = matchReqDto.getInfo().getParticipants();

            List<Participant> participants = participantReqDtos.stream()
                    .map(ParticipantReqDto::toEntity)
                    .collect(Collectors.toList());

            participants = participants.stream()
                    .peek(x -> x.setMatch(match))
                    .collect(Collectors.toList());

            participantRepository.saveAll(participants);

            return match.getId();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }

    public List<String> findMatchList(String puuid, int start, int count) {
        String url = String.format("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%d&count=%d", puuid, start, count);
        try {
            return  riotApi.callApi(url, List.class);
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }
}
