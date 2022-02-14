package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.domain.BaseTimeEntity;
import com.lolplanet.demo.domain.participant.Participant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="game_match")
public class Match extends BaseTimeEntity {

    @Id
    private Long gameId;
    private Integer queueId;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;

    @OneToMany(mappedBy = "id.match")
    private List<Participant> participants = new ArrayList<>();

    @Builder
    public Match(Long gameId, Integer queueId, String platformId, Long gameCreation, Long gameDuration) {
        this.gameId = gameId;
        this.queueId = queueId;
        this.platformId = platformId;
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
    }
}
