package com.lolplanet.demo.api.riot.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SummonerDto {
    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private String id;
    private String puuid;
    private long summonerLevel;
}
