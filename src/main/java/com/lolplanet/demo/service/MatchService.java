package com.lolplanet.demo.service;

import com.lolplanet.demo.api.riot.RiotApi;
import com.lolplanet.demo.api.riot.dto.match.MatchReqDto;
import com.lolplanet.demo.domain.match.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final RiotApi riotApi;

    @Transactional
    public Long save(String matchId) {
        String url = String.format("https://asia.api.riotgames.com/lol/match/v5/matches/%s", matchId);

        try {
            MatchReqDto matchReqDto = riotApi.callApi(url, MatchReqDto.class);
            return matchRepository.save(matchReqDto.toEntity()).getId();
        } catch (HttpClientErrorException ex) {
            throw ex;
        }
    }
}
