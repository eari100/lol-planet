package com.lolplanet.demo.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DbColumnConverterTests {

    DbColumnConverter dbColumnConverter = new DbColumnConverter();

    @Test
    public void kda계산() {
        assertThat(dbColumnConverter.convertToKda(4, 8, 6)).isEqualTo("1.25:1 평점");
    }
}
