package com.lolplanet.demo.service;

import com.lolplanet.demo.api.riot.RiotApi;
import com.lolplanet.demo.api.riot.dto.SummonerReqDto;
import com.lolplanet.demo.domain.summoner.Summoner;
import com.lolplanet.demo.domain.summoner.SummonerRepository;
import com.lolplanet.demo.web.dto.SummonerResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummonerService {

    private final SummonerRepository summonerRepository;
    private final RiotApi riotApi;

    @Transactional
    public SummonerResDto findByName(String name) {
        Optional<Summoner> summoner = summonerRepository.findByName(name);

        if(summoner.isEmpty()) {
            // riot API 호출
            String delimitedUrl = String.format("lol/summoner/v4/summoners/by-name/%s", name);
            SummonerReqDto reqDto;
            try {
                reqDto = riotApi.callApi(delimitedUrl, SummonerReqDto.class);
            } catch (HttpClientErrorException ex) {
                return new SummonerResDto();
            }

            // DB에 저장
            Summoner newSummoner = summonerRepository.save(reqDto.toEntity());
            return new SummonerResDto(newSummoner);
        } else {
            return new SummonerResDto(summoner.get());
        }
    }
}
