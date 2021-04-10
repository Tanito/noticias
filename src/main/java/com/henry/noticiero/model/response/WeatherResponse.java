package com.henry.noticiero.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherResponse {

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("weather")
    private WeatherElement[] weather;

    @SerializedName("base")
    private String base;

    @SerializedName("main")
    private MainResponse mainResponse;

    @SerializedName("visibility")
    private long visibility;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("dt")
    private long dt;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("timezone")
    private long timezone;

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private long cod;

}