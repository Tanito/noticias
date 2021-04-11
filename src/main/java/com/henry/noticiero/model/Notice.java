package com.henry.noticiero.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "noticeEnum")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OnlyText.class, name = "ONLY_TEXT"),
        @JsonSubTypes.Type(value = Image.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = Video.class, name = "VIDEO")
})
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Hace una tabla para cada entidad
public abstract class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message ="El título es obligatorio")
    private String title;

    @NotBlank(message ="La noticia tiene que tener una descripción")
    private String description;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract NoticeEnum noticeEnum();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer writer;
}