package com.henry.noticiero.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Wind {
    private double speed;
    private long deg;
    private double gust;
}
