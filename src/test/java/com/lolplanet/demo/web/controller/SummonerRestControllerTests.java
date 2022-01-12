package com.lolplanet.demo.web.controller;

import com.lolplanet.demo.domain.summoner.Summoner;
import com.lolplanet.demo.domain.summoner.SummonerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Nested
    @DisplayName("findByName 메서드")
    class findByNameTest {

        @AfterEach
        public void cleanUp() {
            summonerRepository.deleteAll();
        }

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
            final String summonerId = "0are3IM-Nf4gD7-wd3uVL1dGEvgsJqUQh25zU6pulSh_nHo";
            final String puuid = "Lp57gEFg0CgfVoALp1vY3RI75siUxQGolH6k0y6X2KCjnHQPdIAbooPBMNcTw-qMn9xbQfIdCT1kng";
            // 현재 레벨보다 낮은 레벨
            final Long pastSummonerLevel = 100L;

            Summoner summoner = Summoner.builder()
                    .accountId(accountId)
                    .profileIconId(profileIconId)
                    .revisionDate(revisionDate)
                    .name(name)
                    .summonerId(summonerId)
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
}
