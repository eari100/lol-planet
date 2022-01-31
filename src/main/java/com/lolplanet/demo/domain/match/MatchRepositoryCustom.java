package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.web.dto.MatchResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchRepositoryCustom {
    Page<MatchResDto> findList(Pageable pageable, String summonerName);
}
