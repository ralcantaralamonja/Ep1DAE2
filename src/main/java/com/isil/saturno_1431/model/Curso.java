package com.isil.saturno_1431.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data //DATA - LOMBOOK --> CONE SO CREAMOS LOS METODOS GET Y SET
@Entity // ENTITY - JAVA PERSISTE --> CURSO EXISTE UNA TABLA EN LA BASE DE DATOS

public class Curso {

    @Id //para defiir primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para definir que es autoincremental
    private Integer id;

    @Column(unique = true)
    private String nrc;

    private String nombre;
    private Integer creditos;
    private Integer horas;
    private String modalidad;
    private String area;
    private LocalDateTime fecha_creacion; //fechaCreacion
    private LocalDateTime fecha_actualizacion; //fechaActualizacion

    @PrePersist // pre(antes de insertar asigna el valor en la fecha de creación)
    void prePersis(){
        fecha_creacion = LocalDateTime.now();
    }

    @PreUpdate //pre(antes de actualizar asigna el valor en la fecha de actualización)
    void preUpdate(){
        fecha_actualizacion = LocalDateTime.now();
    }

    //**existe postPersist
}
