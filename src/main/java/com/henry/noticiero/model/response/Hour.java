package com.henry.noticiero.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hour {

    @SerializedName("temp")
    private String temp;
}