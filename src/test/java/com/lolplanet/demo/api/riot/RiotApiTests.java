package com.lolplanet.demo.api.riot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RiotApiTests {

    @Autowired
    RiotApi riotApi;

    @Test
    public void SummonerApi_test() {
        String summonerName = "한남동의 황제";
        String delimitedUrl = String.format("lol/summoner/v4/summoners/by-name/%s", summonerName);

        Object object = riotApi.callApi(delimitedUrl);

        System.out.println("결과 값 반환: "+object.toString());
    }
}
