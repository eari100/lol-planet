package com.lolplanet.demo.api.riot.dto.match;

import lombok.Getter;

@Getter
public class ObjectivesReqDto {
    private ObjectiveReqDto baron;
    private ObjectiveReqDto champion;
    private ObjectiveReqDto dragon;
    private ObjectiveReqDto inhibitor;
    private ObjectiveReqDto riftHerald;
    private ObjectiveReqDto tower;
}
