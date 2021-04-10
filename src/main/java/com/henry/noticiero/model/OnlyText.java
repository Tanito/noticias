package com.henry.noticiero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class OnlyText extends Notice {

    @Override
    public NoticeEnum noticeEnum(){
        return NoticeEnum.ONLY_TEXT;
    }


}
