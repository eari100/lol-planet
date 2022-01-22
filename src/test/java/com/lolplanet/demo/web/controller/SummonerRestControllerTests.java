package com.lolplanet.demo.web.controller;

import com.lolplanet.demo.domain.match.Match;
import com.lolplanet.demo.domain.match.MatchRepository;
import com.lolplanet.demo.domain.participant.Participant;
import com.lolplanet.demo.domain.participant.ParticipantRepository;
import com.lolplanet.demo.domain.summoner.Summoner;
import com.lolplanet.demo.domain.summoner.SummonerRepository;
import com.lolplanet.demo.service.MatchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SummonerRestController 클래스")
public class SummonerRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SummonerRepository summonerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private MatchService matchService;

    @AfterEach
    public void cleanUp() {
        participantRepository.deleteAll();
        matchRepository.deleteAll();
        summonerRepository.deleteAll();
    }

    @Nested
    @DisplayName("findByName method")
    class findByNameTest {

        private final String BASE_URL = "/lol/summoner/v4/summoners/by-name/";

        @Test
        @DisplayName(
            "1. DB에 데이터가 존재x " +
            "2. Riot 서버에 데이터가 존재o "
        )
        public void 새로운_데이터가_저장된다() throws Exception {
            final String name = "한남동의 황제";

            mockMvc.perform(get(BASE_URL + name))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.profileIconId").exists())
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$.summonerLevel", greaterThan(300)));
        }

        @Test
        @DisplayName(
            "1. DB에 데이터가 존재o " +
            "2. Riot 서버에 데이터가 존재o "
        )
        public void Riot_서버에서_데이터를_호출되지_않고_DB에서_조회된다() throws Exception {
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

            mockMvc.perform(get(BASE_URL + name))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.profileIconId", is(profileIconId)))
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$.summonerLevel").value(pastSummonerLevel));
        }

        @Test
        @DisplayName(
            "1. DB에 데이터가 존재x " +
            "2. Riot 서버에 데이터가 존재x "
        )
        public void DB에_저장되지_않는다() throws Exception {
            final String NON_EXISTENT_NAME = "재욱의환상제드123";

            mockMvc.perform(get(BASE_URL + NON_EXISTENT_NAME))
                    .andDo(print())
                    .andExpect(jsonPath("$.profileIconId").doesNotExist())
                    .andExpect(jsonPath("$.name").doesNotExist())
                    .andExpect(jsonPath("$.summonerLevel").doesNotExist())
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void match_participant_정보_갱신_후_조회() throws Exception {
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

        mockMvc.perform(post("/lol/summoner/renew/by-name/" + name))
                .andDo(print())
                .andExpect(status().isOk());

        Summoner updatedSummoner = summonerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 소환사가 없습니다 id=" + id));
        assertThat(updatedSummoner.getSummonerLevel()).isGreaterThan(pastSummonerLevel);

        List<Match> matches = matchRepository.findAll();
        assertThat(matches.size()).isGreaterThan(0);

        List<Participant> participants = participantRepository.findAll();
        assertThat(participants.size()).isGreaterThan(9); // 소환사는 최소 10명
    }
}
