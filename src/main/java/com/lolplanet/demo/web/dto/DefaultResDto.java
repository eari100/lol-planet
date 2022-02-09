package com.lolplanet.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DefaultResDto<T> {
    private T data;
    private ErrorStatus errorStatus;
}
