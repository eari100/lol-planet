package com.lolplanet.demo.web.dto;

import com.lolplanet.demo.domain.participant.Participant;
import lombok.Getter;

@Getter
public class ParticipantResDto {
    private String championName;
    private Integer teamId;
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
    private Integer participantId;

    public ParticipantResDto(Participant entity) {

        DbColumnConverter converter = new DbColumnConverter();

        this.championName = converter.convertToChampionName(entity.getChampionId());
        this.teamId = entity.getTeamId();
        this.summoner1Id = entity.getSummoner1Id();
        this.summoner2Id = entity.getSummoner2Id();
        this.item0 = entity.getItem0();
        this.item1 = entity.getItem1();
        this.item2 = entity.getItem2();
        this.item3 = entity.getItem3();
        this.item4 = entity.getItem4();
        this.item5 = entity.getItem5();
        this.item6 = entity.getItem6();
        this.kills = entity.getKills();
        this.deaths = entity.getDeaths();
        this.assists = entity.getAssists();
        this.champLevel = entity.getChampLevel();
        this.totalMinionsKilled = entity.getTotalMinionsKilled();
        this.neutralMinionsKilled = entity.getNeutralMinionsKilled();
        this.visionWardsBoughtInGame = entity.getVisionWardsBoughtInGame();
        this.win = entity.getWin();
        this.summonerName = entity.getSummonerName();
        this.participantId = entity.getParticipantId();
    }
}
