package com.lolplanet.demo.domain.summoner;

import com.lolplanet.demo.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Summoner extends BaseTimeEntity {

    @Id
    @Column(length=63)
    private String id;

    @Column(length=56)
    private String accountId;

    private Integer profileIconId;

    private Long revisionDate;

    private String name;

    @Column(length=78)
    private String puuid;

    private Long summonerLevel;

    @Builder
    public Summoner(String id, String accountId, Integer profileIconId, Long revisionDate, String name, String puuid, Long summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }

    public void update(String accountId, Integer profileIconId, Long revisionDate, String name, String puuid, Long summonerLevel) {
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }
}
