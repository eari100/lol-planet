package com.lolplanet.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ErrorStatus {
    private Integer statusCode;
    private String message;

    @SuppressWarnings("unchecked")
    @JsonProperty("status")
    private void unpackNested(Map<String,Object> status) {
        this.statusCode = (Integer)status.get("status_code");
        this.message = (String)status.get("message");
    }
}
