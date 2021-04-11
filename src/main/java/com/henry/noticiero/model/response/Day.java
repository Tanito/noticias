package com.henry.noticiero.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Day {
    @SerializedName("symbol_description")
    private String status;

    @SerializedName("tempmin")
    private String tempmin;

    @SerializedName("tempmax")
    private String tempmax;

    @SerializedName("hour")
    private Hour[] hour;
}