package com.isil.saturno_1431.model;


import lombok.Data;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;


@Entity
@Data
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String nombres;
    private String apellidos;
    @Column(unique = true)
    private  Integer dni;
    private  String correo;
    @ManyToOne
    @JoinColumn(name = "id_carrera") // Especifica el nombre de la columna de la clave foránea en la tabla Estudiante
    private Carrera carrera;

}
