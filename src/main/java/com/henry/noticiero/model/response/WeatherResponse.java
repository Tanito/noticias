package com.henry.noticiero.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class WeatherResponse {

    private String status;
    private String temp;
    private String tempMin;
    private String tempMax;
}