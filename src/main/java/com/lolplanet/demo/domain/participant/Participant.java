package com.lolplanet.demo.domain.participant;

import com.lolplanet.demo.domain.BaseTimeEntity;
import com.lolplanet.demo.domain.match.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
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

    @Builder
    public Participant(Integer championId, Integer teamId, Integer summoner1Id, Integer summoner2Id,
            Integer item0, Integer item1, Integer item2, Integer item3, Integer item4, Integer item5, Integer item6,
            Integer kills, Integer deaths, Integer assists, Integer champLevel,
            Integer totalMinionsKilled, Integer neutralMinionsKilled, Integer visionWardsBoughtInGame,
            Boolean win, String summonerName, String summonerId, Match match, Integer participantId) {

        this.championId = championId;
        this.teamId = teamId;
        this.summoner1Id = summoner1Id;
        this.summoner2Id = summoner2Id;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.champLevel = champLevel;
        this.totalMinionsKilled = totalMinionsKilled;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.win = win;
        this.summonerName = summonerName;
        this.summonerId = summonerId;
        this.match = match;
        this.participantId = participantId;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
