package com.lolplanet.demo.web.dto;

import com.lolplanet.demo.domain.match.Match;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MatchResDto {
    private String mapName;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;
    private List<ParticipantResDto> participantResDtos = new ArrayList<>();

    public MatchResDto(Match entity) {
        DbColumnConverter converter = new DbColumnConverter();

        this.mapName = converter.convertToMapName(entity.getQueueId());
        this.platformId = entity.getPlatformId();
        this.gameCreation = entity.getGameCreation();
        this.gameDuration = entity.getGameDuration();
        this.participantResDtos = entity.getParticipants().stream()
                .map(ParticipantResDto::new)
                .collect(Collectors.toList());
    }
}
