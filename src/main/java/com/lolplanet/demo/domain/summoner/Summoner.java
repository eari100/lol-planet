package com.lolplanet.demo.domain.summoner;

import javax.persistence.*;

@Entity
public class Summoner {

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
}
