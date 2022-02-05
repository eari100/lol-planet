package com.lolplanet.demo.domain.participant;

import com.lolplanet.demo.web.dto.MatchListByNameResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipantRepositoryCustom {
    Page<MatchListByNameResDto> findBySummonerId(Pageable pageable, String summonerId);
}
