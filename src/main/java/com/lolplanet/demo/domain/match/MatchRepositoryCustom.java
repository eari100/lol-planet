package com.lolplanet.demo.domain.match;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchRepositoryCustom {
    List<Match> findList(Pageable pageable, String summonerName);
}
