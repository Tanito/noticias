package com.henry.noticiero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.net.URL;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@Entity
public class Image extends Notice {

    private ArrayList<URL> photosList;

    @Override
    public NoticeEnum noticeEnum(){
        return NoticeEnum.IMAGE;
    }




}
