package com.lolplanet.demo.domain.participant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipantRepositoryCustom {
    Page<Participant> findBysummonerId(Pageable pageable, String summonerId);
}
