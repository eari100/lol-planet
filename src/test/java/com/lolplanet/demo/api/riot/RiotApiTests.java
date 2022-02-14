package com.lolplanet.demo.api.riot;

import com.lolplanet.demo.api.riot.dto.SummonerReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest
public class RiotApiTests {

    @Autowired
    RiotApi riotApi;

    @Test
    public void 라이엇API에서_소환사_데이터_가져오기() {
        String summonerName = "한남동의 황제";
        String delimitedUrl = String.format("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s", summonerName);

        SummonerReqDto summonerReqDto = riotApi.callApi(delimitedUrl, SummonerReqDto.class);

        System.out.println(summonerName + " 의 소환사 정보: "+ summonerReqDto.toString());

        assertThat(summonerReqDto.getName()).isEqualTo(summonerName);
    }
}
