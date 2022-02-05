package com.lolplanet.demo.domain;

import com.lolplanet.demo.domain.match.Match;
import com.lolplanet.demo.domain.match.MatchRepository;
import com.lolplanet.demo.domain.participant.Participant;
import com.lolplanet.demo.domain.participant.ParticipantId;
import com.lolplanet.demo.domain.participant.ParticipantRepository;
import com.lolplanet.demo.web.dto.MatchListByNameResDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ParticipantRepositoryTests {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @AfterEach
    public void cleanUp() {
        participantRepository.deleteAll();
        matchRepository.deleteAll();
    }

    @Test
    @Transactional // org.hibernate.LazyInitializationException: could not initialize proxy 방지
    public void 매치_리스트_조회() {

        Match match1 = Match.builder()
                .gameId(5695889101L)
                .queueId(430)
                .platformId("KR")
                .gameCreation(1642338610000L)
                .gameDuration(1429L)
                .build();

        matchRepository.save(match1);

        Match lazyMatch1 = matchRepository.getOne(5695889101L);

        Participant participant1 = Participant.builder()
                .championId(54)
                .teamId(100)
                .summoner1Id(12)
                .summoner2Id(4)
                .item0(0)
                .item1(0)
                .item2(4636)
                .item3(3145)
                .item4(0)
                .item5(3020)
                .item6(3364)
                .kills(1)
                .deaths(1)
                .assists(1)
                .champLevel(18)
                .totalMinionsKilled(4)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(0)
                .win(false)
                .summonerName("유땡글돌머리")
                .id(new ParticipantId("Q3WiCl2U9gEiDt92dcALahBD21dyRyy7NwyyeKx9TfH42PK3", lazyMatch1))
                .participantId(1)
                .build();

        Participant participant2 = Participant.builder()
                .championId(350)
                .teamId(100)
                .summoner1Id(3)
                .summoner2Id(14)
                .item0(3853)
                .item1(1052)
                .item2(6617)
                .item3(3011)
                .item4(1052)
                .item5(3114)
                .item6(3364)
                .kills(2)
                .deaths(2)
                .assists(2)
                .champLevel(18)
                .totalMinionsKilled(4)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(0)
                .win(false)
                .summonerName("sunyy")
                .id(new ParticipantId("wUIRBztF3yQVEqMqaIeMkJXdCgO--ScoLTm0ElpzZcozoQ", lazyMatch1))
                .participantId(2)
                .build();

        Participant participant3 = Participant.builder()
                .championId(101)
                .teamId(100)
                .summoner1Id(21)
                .summoner2Id(4)
                .item0(1056)
                .item1(3070)
                .item2(1001)
                .item3(3802)
                .item4(0)
                .item5(0)
                .item6(3340)
                .kills(3)
                .deaths(3)
                .assists(3)
                .champLevel(18)
                .totalMinionsKilled(4)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(0)
                .win(false)
                .summonerName("윤석현3")
                .id(new ParticipantId("3-IsNMNIVy30ldzJfegVCNdDNbinslhGOf1siYh3-XiXwOc", lazyMatch1))
                .participantId(3)
                .build();

        Participant participant4 = Participant.builder()
                .championId(23)
                .teamId(100)
                .summoner1Id(6)
                .summoner2Id(4)
                .item0(6671)
                .item1(6675)
                .item2(3006)
                .item3(3033)
                .item4(0)
                .item5(0)
                .item6(3340)
                .kills(4)
                .deaths(4)
                .assists(4)
                .champLevel(18)
                .totalMinionsKilled(0)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(4)
                .win(false)
                .summonerName("겐지나")
                .id(new ParticipantId("5BnI19qqt-tNMOpRlCMu0MG2FcLOfec1qQQpZZc8NOFi8Xo", lazyMatch1))
                .participantId(4)
                .build();

        Participant participant5 = Participant.builder()
                .championId(11)
                .teamId(100)
                .summoner1Id(11)
                .summoner2Id(4)
                .item0(6672)
                .item1(6333)
                .item2(3006)
                .item3(3124)
                .item4(1037)
                .item5(1036)
                .item6(3364)
                .kills(5)
                .deaths(5)
                .assists(5)
                .champLevel(18)
                .totalMinionsKilled(0)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(4)
                .win(false)
                .summonerName("0민수")
                .id(new ParticipantId("zWJ-do6nOElM_4odEmMB-ATSYI525sU_cMfjPway6aBoJs8", lazyMatch1))
                .participantId(5)
                .build();

        Participant participant6 = Participant.builder()
                .championId(555)
                .teamId(200)
                .summoner1Id(14)
                .summoner2Id(4)
                .item0(3117)
                .item1(6691)
                .item2(3855)
                .item3(3134)
                .item4(0)
                .item5(1036)
                .item6(3364)
                .kills(6)
                .deaths(6)
                .assists(6)
                .champLevel(18)
                .totalMinionsKilled(0)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(4)
                .win(true)
                .summonerName("HarmlesScarecrow")
                .id(new ParticipantId("WS7xjzZDbSGeVZAu1OrMXwxJlZo9nngTtyLPiblq3D7Jmmw", lazyMatch1))
                .participantId(6)
                .build();

        Participant participant7 = Participant.builder()
                .championId(106)
                .teamId(200)
                .summoner1Id(11)
                .summoner2Id(4)
                .item0(4633)
                .item1(1028)
                .item2(3020)
                .item3(3115)
                .item4(3157)
                .item5(1082)
                .item6(3340)
                .kills(7)
                .deaths(7)
                .assists(7)
                .champLevel(18)
                .totalMinionsKilled(0)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(4)
                .win(true)
                .summonerName("사과티비개발자")
                .id(new ParticipantId("6II0yzTrICAMCf9Q2gBEKnY_T3goExoww8J8DCHYSK56f64", lazyMatch1))
                .participantId(7)
                .build();

        Participant participant8 = Participant.builder()
                .championId(875)
                .teamId(200)
                .summoner1Id(12)
                .summoner2Id(4)
                .item0(6630)
                .item1(3044)
                .item2(1037)
                .item3(3047)
                .item4(0)
                .item5(0)
                .item6(3364)
                .kills(8)
                .deaths(8)
                .assists(8)
                .champLevel(18)
                .totalMinionsKilled(0)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(4)
                .win(true)
                .summonerName("니노는사랑입니다")
                .id(new ParticipantId("wF99smx6fsE8hOAuhSm96R_0CJkJpgoy7tGT6eCqxFLQR50", lazyMatch1))
                .participantId(8)
                .build();

        Participant participant9 = Participant.builder()
                .championId(157)
                .teamId(200)
                .summoner1Id(14)
                .summoner2Id(4)
                .item0(6673)
                .item1(3111)
                .item2(2055)
                .item3(1037)
                .item4(1038)
                .item5(1018)
                .item6(3364)
                .kills(9)
                .deaths(9)
                .assists(9)
                .champLevel(18)
                .totalMinionsKilled(0)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(4)
                .win(true)
                .summonerName("한남동의 황제")
                .id(new ParticipantId("0are3IM-Nf4gD7-wd3uVL1dGEvgsJqUQh25zU6pulSh_nHo", lazyMatch1))
                .participantId(9)
                .build();

        Participant participant10 = Participant.builder()
                .championId(360)
                .teamId(200)
                .summoner1Id(4)
                .summoner2Id(7)
                .item0(6673)
                .item1(3047)
                .item2(1055)
                .item3(3036)
                .item4(1037)
                .item5(0)
                .item6(3363)
                .kills(0)
                .deaths(0)
                .assists(0)
                .champLevel(18)
                .totalMinionsKilled(0)
                .neutralMinionsKilled(0)
                .visionWardsBoughtInGame(4)
                .win(true)
                .summonerName("T1 Thinking")
                .id(new ParticipantId("F0u8AV4C0nEIprSMA24B4KSBqpgkDEtCelzUNiIF2Wnp8Lw", lazyMatch1))
                .participantId(10)
                .build();

        List<Participant> participants = Arrays.asList(
                participant1, participant2, participant3, participant4, participant5,
                participant6, participant7, participant8, participant9, participant10);

        participantRepository.saveAll(participants);

        final int count = 1;

        Page<MatchListByNameResDto> dto = participantRepository.findBySummonerId((PageRequest.of(0, count, Sort.by("gameCreation").descending())), "0are3IM-Nf4gD7-wd3uVL1dGEvgsJqUQh25zU6pulSh_nHo");

        assertThat(dto.getNumber()).isEqualTo(0);
        assertThat(dto.getTotalPages()).isEqualTo(1);
        assertThat(dto.getContent().size()).isEqualTo(1);

        for(MatchListByNameResDto mlbnDto : dto) {
            assertThat(mlbnDto.getSummonerName()).isNotNull();
        }
    }
}
