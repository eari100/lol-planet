package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Match extends BaseTimeEntity {

    @Id
    private Long gameId;
    private Integer queueId;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;

    @Builder
    public Match(Long gameId, Integer queueId, String platformId, Long gameCreation, Long gameDuration) {
        this.gameId = gameId;
        this.queueId = queueId;
        this.platformId = platformId;
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
    }
}
