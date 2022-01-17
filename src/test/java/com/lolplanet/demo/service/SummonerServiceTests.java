package com.lolplanet.demo.service;

import com.lolplanet.demo.domain.summoner.Summoner;
import com.lolplanet.demo.domain.summoner.SummonerRepository;
import com.lolplanet.demo.web.dto.SummonerResDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SummonerServiceTests {
    @Autowired
    private SummonerService summonerService;

    @Autowired
    private SummonerRepository summonerRepository;

    @AfterEach
    private void cleanUp() {
        summonerRepository.deleteAll();
    }

    @Test
    public void 소환사_정보가_갱신된다() {
        final String accountId = "vXUy9xx-2r13SGuV0gdvnJPM8hGWhAKNekdH7QHtraAi";
        final Integer profileIconId = 4001;
        final Long revisionDate = 1641028234000L;
        final String name = "한남동의 황제";
        final String id = "0are3IM-Nf4gD7-wd3uVL1dGEvgsJqUQh25zU6pulSh_nHo";
        final String puuid = "Lp57gEFg0CgfVoALp1vY3RI75siUxQGolH6k0y6X2KCjnHQPdIAbooPBMNcTw-qMn9xbQfIdCT1kng";
        // 현재 레벨보다 낮은 레벨
        final Long pastSummonerLevel = 100L;

        Summoner summoner = Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(pastSummonerLevel)
                .build();
        summonerRepository.save(summoner);

        SummonerResDto dto = summonerService.update(name);
        assertThat(dto.getSummonerLevel()).isGreaterThan(pastSummonerLevel);

        List<Summoner> summonerList = summonerRepository.findAll();
        assertThat(summonerList.size()).isEqualTo(1);
    }
}
