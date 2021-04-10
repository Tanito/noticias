package com.henry.noticiero.model;


public enum NoticeEnum {

    ONLY_TEXT("Only text"),
    VIDEO("Text with video"),
    IMAGE("Text with image");

    private String description;

   NoticeEnum(String description) {
       this.description = description;
   }

    public String getDescription() {
        return description;
    }

    public static NoticeEnum find(String value){
       for (NoticeEnum n : values()){
           if (n.toString().equalsIgnoreCase(value)){
               return n;
           }
       }
        throw new IllegalArgumentException(String.format("Invalid noticeType: %s", value));
    }
}
