package com.lolplanet.demo.web.dto;

import com.lolplanet.demo.domain.summoner.Summoner;
import lombok.Getter;

@Getter
public class SummonerResDto {
    private Integer profileIconId;
    private String name;
    private Long summonerLevel;

    public SummonerResDto(Summoner entity) {
        this.profileIconId = entity.getProfileIconId();
        this.name = entity.getName();
        this.summonerLevel = entity.getSummonerLevel();
    }
}
