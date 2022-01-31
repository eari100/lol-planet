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
    private List<ParticipantResDto> participantResDtos = new ArrayList<>();

    public MatchResDto(Match entity, String summonerName) {
        DbColumnConverter converter = new DbColumnConverter();

        this.mapName = converter.convertToMapName(entity.getQueueId());
        this.platformId = entity.getPlatformId();
        this.gameCreationToDate = converter.convertToGameCreationDate(entity.getGameCreation());
        this.gameDurationToString = converter.convertToGameDurationMinutesSeconds(entity.getGameDuration());

        for(Participant participant : entity.getParticipants()) {
            participantResDtos.add(new ParticipantResDto(participant));

            if(participant.getSummonerName().equals(summonerName)) {
                this.gameResult = converter.convertToGameResult(participant.getWin());
                this.playedChampionName = converter.convertToChampionName(participant.getChampionId());
            }
        }
    }
}
