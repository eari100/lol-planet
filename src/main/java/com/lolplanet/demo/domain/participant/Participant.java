package com.lolplanet.demo.domain.participant;

import com.lolplanet.demo.domain.BaseTimeEntity;
import com.lolplanet.demo.domain.match.Match;

import javax.persistence.*;

@Entity
public class Participant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer championId;
    private Integer teamId;

    // spell
    private Integer summoner1Id;
    private Integer summoner2Id;

    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer champLevel;
    private Integer totalMinionsKilled;
    private Integer neutralMinionsKilled;
    private Integer visionWardsBoughtInGame;
    private Boolean win;
    private String summonerName;

    @Column(length=56)
    private String summonerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Match match;

    // 1 ~ 10
    private Integer participantId;
}
