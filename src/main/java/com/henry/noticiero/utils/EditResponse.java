package com.henry.noticiero.utils;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class EditResponse {


    private HttpStatus status;
}
