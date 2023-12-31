package com.isil.saturno_1431.model;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotBlank
    @NotNull
    private String nombres;
    @NotBlank
    @NotNull
    private String apellidos;

    @Column(unique=true)
    @NotNull
    @Size( min = 8)
    private  String dni;
    @NotNull
    @NotBlank
    private  String correo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_carrera") // Especifica el nombre de la columna de la clave foránea en la tabla Estudiante
    private Carrera carrera;

}
