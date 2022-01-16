package com.lolplanet.demo.api.riot.dto;

import com.lolplanet.demo.domain.summoner.Summoner;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SummonerReqDto {
    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private String summonerId;
    private String puuid;
    private long summonerLevel;

    public Summoner toEntity() {
       return Summoner.builder().accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .summonerId(summonerId)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build();
    }
}
