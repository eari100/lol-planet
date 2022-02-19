package com.lolplanet.demo.web.dto;

import com.lolplanet.demo.domain.match.Match;
import com.lolplanet.demo.domain.participant.Participant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MatchListBySummonerResDto {
    private String mapName;
    private String platformId;
    private String gameCreationToDate;
    private String gameDurationToString;

    private String playedChampionName;
    private String usedSpell1Name;
    private String usedSpell2Name;

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
    private String kda;

    private Integer playedChampLevel;
    private Integer totalMinionsKilled;
    private Integer neutralMinionsKilled;
    private Integer totalCs;
    private String csPerMinute;

    private Integer visionWardsBoughtInGame;
    private Boolean win;
    private String gameResult;
    private String summonerName;
    private Integer participantId;
    private List<ParticipantResDto> blueTeam = new ArrayList<>();
    private List<ParticipantResDto> redTeam = new ArrayList<>();

    public MatchListBySummonerResDto(Participant entity) {
        DbColumnConverter converter = new DbColumnConverter();
        Match match = entity.getId().getMatch();

        this.mapName = converter.convertToMapName(match.getQueueId());
        this.platformId = match.getPlatformId();
        this.gameCreationToDate = converter.convertToGameCreationDate(match.getGameCreation());
        this.gameDurationToString = converter.convertToGameDurationMinutesSeconds(match.getGameDuration());

        this.playedChampionName = converter.convertToChampionName(entity.getChampionId());
        this.usedSpell1Name = converter.convertToSpellName(entity.getSummoner1Id());
        this.usedSpell2Name = converter.convertToSpellName(entity.getSummoner2Id());

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
        this.kda = converter.convertToKda(this.kills, this.deaths, this.assists);

        this.playedChampLevel = entity.getChampLevel();
        this.totalMinionsKilled = entity.getTotalMinionsKilled();
        this.neutralMinionsKilled = entity.getNeutralMinionsKilled();
        this.totalCs = this.totalMinionsKilled + this.neutralMinionsKilled;
        this.csPerMinute = converter.convertToCsPerMinute(this.totalCs, match.getGameDuration());

        this.visionWardsBoughtInGame = entity.getVisionWardsBoughtInGame();
        this.win = entity.getWin();
        this.gameResult = converter.convertToGameResult(this.win);
        this.summonerName = entity.getSummonerName();
        this.participantId = entity.getParticipantId();

        entity.getId().getMatch().getParticipants()
            .forEach(p -> {
                if (p.getTeamId() == 100) {
                    blueTeam.add(new ParticipantResDto(p));
                } else if (p.getTeamId() == 200) {
                    redTeam.add(new ParticipantResDto(p));
                }
            });
    }
}
