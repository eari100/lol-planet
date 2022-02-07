package com.lolplanet.demo.web.dto;

import com.lolplanet.demo.domain.summoner.Summoner;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummonerResDto extends StatusResDto {
    private Integer profileIconId;
    private String name;
    private Long summonerLevel;
    private String puuid;

    public SummonerResDto(Summoner entity) {
        this.profileIconId = entity.getProfileIconId();
        this.name = entity.getName();
        this.summonerLevel = entity.getSummonerLevel();
        this.puuid = entity.getPuuid();
    }

    public void setStatus(StatusResDto status) {
        this.setStatusCode(status.getStatusCode());
        this.setMessage(status.getMessage());
    }
}
