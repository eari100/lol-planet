package com.lolplanet.demo.domain.participant;

import com.lolplanet.demo.web.dto.MatchListBySummonerResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipantRepositoryCustom {
    Page<MatchListBySummonerResDto> findBySummonerId(Pageable pageable, String summonerId);
}
