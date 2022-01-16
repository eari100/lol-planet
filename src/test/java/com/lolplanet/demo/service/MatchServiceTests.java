package com.lolplanet.demo.service;


import com.lolplanet.demo.domain.match.Match;
import com.lolplanet.demo.domain.match.MatchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MatchServiceTests {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;

    @AfterEach
    public void cleanUp() {
        matchRepository.deleteAll();
    }

    @Test
    public void 매치_저장() {
        matchService.save("KR_5664585283");
        List<Match> matchList = matchRepository.findAll();
        assertThat(matchList.get(0).getId()).isNotNull();
    }

    @Test
    public void 소환사의_매치리스트_조회() {
        List<String> matchList = matchService.findMatchList("Lp57gEFg0CgfVoALp1vY3RI75siUxQGolH6k0y6X2KCjnHQPdIAbooPBMNcTw-qMn9xbQfIdCT1kng", 0, 20);
        assertThat(matchList.size()).isEqualTo(20);
    }
}
