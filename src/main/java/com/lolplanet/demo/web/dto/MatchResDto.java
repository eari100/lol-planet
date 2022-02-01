package com.lolplanet.demo.web.dto;

import com.lolplanet.demo.domain.match.Match;
import com.lolplanet.demo.domain.participant.Participant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MatchResDto {
    private String mapName;
    private String platformId;
    private String gameCreationToDate;
    private String gameDurationToString;
    private String gameResult;
    private String playedChampionName;
    private String usedSpell1Name;
    private String usedSpell2Name;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private String kda;
    private Integer playedChampLevel;
    private Integer totalMinionsKilled;
    private Integer neutralMinionsKilled;
    private Integer totalCs;
    private String csPerMinute;
    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;
    private Integer visionWardsBoughtInGame;
    private List<ParticipantResDto> participants = new ArrayList<>();

    public MatchResDto(Match entity, String summonerName) {
        DbColumnConverter converter = new DbColumnConverter();

        this.mapName = converter.convertToMapName(entity.getQueueId());
        this.platformId = entity.getPlatformId();
        this.gameCreationToDate = converter.convertToGameCreationDate(entity.getGameCreation());
        this.gameDurationToString = converter.convertToGameDurationMinutesSeconds(entity.getGameDuration());

        for(Participant participant : entity.getParticipants()) {
            participants.add(new ParticipantResDto(participant));

            if(participant.getSummonerName().equals(summonerName)) {
                this.gameResult = converter.convertToGameResult(participant.getWin());
                this.playedChampionName = converter.convertToChampionName(participant.getChampionId());
                this.usedSpell1Name = converter.convertToSpellName(participant.getSummoner1Id());
                this.usedSpell2Name = converter.convertToSpellName(participant.getSummoner2Id());
                this.kills = participant.getKills();
                this.deaths = participant.getDeaths();
                this.assists = participant.getAssists();
                this.kda = converter.convertToKda(participant.getKills(), participant.getDeaths(), participant.getAssists());
                this.playedChampLevel = participant.getChampLevel();
                this.totalMinionsKilled = participant.getTotalMinionsKilled();
                this.neutralMinionsKilled = participant.getNeutralMinionsKilled();
                this.totalCs = participant.getTotalMinionsKilled() + participant.getNeutralMinionsKilled();
                this.csPerMinute = converter.convertToCsPerMinute(this.totalCs, entity.getGameDuration());
                this.item0 = participant.getItem0();
                this.item1 = participant.getItem1();
                this.item2 = participant.getItem2();
                this.item3 = participant.getItem3();
                this.item4 = participant.getItem4();
                this.item5 = participant.getItem5();
                this.item6 = participant.getItem6();
                this.visionWardsBoughtInGame = participant.getVisionWardsBoughtInGame();
            }
        }
    }
}
