package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.domain.BaseTimeEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Match extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gameId;
    private Integer queueId;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;
}
