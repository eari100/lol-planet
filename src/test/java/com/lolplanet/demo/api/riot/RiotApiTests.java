package com.lolplanet.demo.api.riot;

import com.lolplanet.demo.api.riot.dto.SummonerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RiotApiTests {

    @Autowired
    RiotApi riotApi;

    @Test
    public void 라이엇API에서_소환사_데이터_가져오기() {
        String summonerName = "한남동의 황제";
        String delimitedUrl = String.format("lol/summoner/v4/summoners/by-name/%s", summonerName);

        SummonerDto summonerDto = riotApi.callApi(delimitedUrl, SummonerDto.class);

        System.out.println(summonerName + " 의 소환사 정보: "+summonerDto.toString());

        assertThat(summonerDto.getName()).isEqualTo(summonerName);
    }
}
