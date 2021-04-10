package com.henry.noticiero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.net.URL;

@Data
@NoArgsConstructor
@Entity
public class Video extends Notice{

    private URL videoUrl;


    @Override
    public NoticeEnum noticeEnum(){
        return NoticeEnum.VIDEO;
    }
}
