package com.lolplanet.demo.api.riot.dto.match;

import com.lolplanet.demo.domain.match.Match;
import lombok.Getter;

@Getter
public class MatchReqDto {
    private MetadataReqDto metadata;
    private InfoReqDto info;

    public Match toEntity() {
        return Match.builder()
                .gameId(info.getGameId())
                .queueId(info.getQueueId())
                .platformId(info.getPlatformId())
                .gameCreation(info.getGameCreation())
                .gameDuration(info.getGameDuration())
                .build();
    }
}
