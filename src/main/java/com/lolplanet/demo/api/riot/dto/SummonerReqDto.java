package com.lolplanet.demo.api.riot.dto;

import com.lolplanet.demo.domain.summoner.Summoner;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SummonerReqDto {
    private String id;
    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private String puuid;
    private long summonerLevel;

    public Summoner toEntity() {
       return Summoner.builder()
               .id(id)
               .accountId(accountId)
               .profileIconId(profileIconId)
               .revisionDate(revisionDate)
               .name(name)
               .puuid(puuid)
               .summonerLevel(summonerLevel)
               .build();
    }
}
