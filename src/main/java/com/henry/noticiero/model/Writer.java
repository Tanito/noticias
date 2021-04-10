package com.henry.noticiero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 7, max = 8, message = "El dni no es válido")
    private String dni;

    @NotNull(message = "El campo Nombre no puede ser vacío.")
    private String nombre;

    private String apellido;


    }
