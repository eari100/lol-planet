package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.domain.BaseTimeEntity;
import com.lolplanet.demo.domain.summoner.Summoner;

import javax.persistence.*;

@Entity
public class SummonerMatch extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Summoner summoner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Match match;
}
