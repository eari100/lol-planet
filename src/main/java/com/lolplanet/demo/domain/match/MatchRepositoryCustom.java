package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.web.dto.MatchListResDto;

public interface MatchRepositoryCustom {
    MatchListResDto findList(int start, int count);
}
