package com.lolplanet.demo.api.riot.dto.match;

import lombok.Getter;

import java.util.List;

@Getter
public class PerkStyleReqDto {
    private String description;
    private List<TeamReqDto> selections;
    private Integer style;
}
