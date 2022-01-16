package com.lolplanet.demo.domain.summoner;

import com.lolplanet.demo.domain.BaseTimeEntity;
import com.lolplanet.demo.domain.participant.Participant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Summoner extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=56)
    private String accountId;

    private Integer profileIconId;

    private Long revisionDate;

    private String name;

    @Column(length=56)
    private String summonerId;

    @Column(length=78)
    private String puuid;

    private Long summonerLevel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "summoner")
    private List<Participant> participants;

    @Builder
    public Summoner(String accountId, Integer profileIconId, Long revisionDate, String name, String summonerId, String puuid, Long summonerLevel) {
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.summonerId = summonerId;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }
}
