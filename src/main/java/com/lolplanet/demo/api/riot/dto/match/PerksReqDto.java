package com.lolplanet.demo.api.riot.dto.match;

import lombok.Getter;

import java.util.List;

@Getter
public class PerksReqDto {
    private PerkStatsReqDto statPerks;
    private List<PerkStyleReqDto> styles;
}
