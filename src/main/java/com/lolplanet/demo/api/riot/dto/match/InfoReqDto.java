package com.lolplanet.demo.api.riot.dto.match;

import lombok.Getter;

import java.util.List;

@Getter
public class InfoReqDto {
    private Long gameCreation;
    private Long gameDuration;
    private Long gameEndTimestamp;
    private Long gameId;
    private String gameMode;
    private String gameName;
    private Long gameStartTimestamp;
    private String gameType;
    private	String gameVersion;
    private Integer mapId;
    private List<ParticipantReqDto> participants;
    private String platformId;
    private Integer queueId;
    List<TeamReqDto> teams;
    private String tournamentCode;
}
