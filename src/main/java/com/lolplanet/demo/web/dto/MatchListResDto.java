package com.lolplanet.demo.web.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MatchListResDto {
    List<MatchResDto> matchResDtos = new ArrayList<>();

    public MatchListResDto(List<MatchResDto> matchResDtos) {
        this.matchResDtos = matchResDtos;
    }
}
