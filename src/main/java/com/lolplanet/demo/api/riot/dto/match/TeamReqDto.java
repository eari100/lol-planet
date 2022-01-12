package com.lolplanet.demo.api.riot.dto.match;

import lombok.Getter;

import java.util.List;

@Getter
public class TeamReqDto {
    private List<BanReqDto> bans;
    ObjectivesReqDto objectives;
    private Integer teamId;
    private Boolean win;
}
