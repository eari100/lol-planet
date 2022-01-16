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

            SummonerReqDto reqDto;
            try {
                reqDto = callSummonerApi(name);
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

    @Transactional
    public SummonerResDto update(String name) {

        SummonerReqDto reqDto;
        try {
            reqDto = callSummonerApi(name);
        } catch (HttpClientErrorException ex) {
            return new SummonerResDto();
        }

        Summoner summoner = summonerRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 소환사가 없습니다. name=" + name));

        summoner.update(reqDto.getAccountId(), reqDto.getProfileIconId(), reqDto.getRevisionDate(),
                reqDto.getName(), reqDto.getSummonerId(), reqDto.getPuuid(), reqDto.getSummonerLevel());

        return new SummonerResDto(summoner);
    }

    private SummonerReqDto callSummonerApi(String name) {
        // riot API 호출
        String url = String.format("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s", name);
        return riotApi.callApi(url, SummonerReqDto.class);
    }
}
