package com.lolplanet.demo.api.riot.dto.match;

import lombok.Getter;

import java.util.List;

@Getter
public class MetadataReqDto {
    private String dataVersion;
    private String matchId;
    private List<String> participants;
}
