package com.isil.saturno_1431.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Carrera {
    /*id INT auto_increment PRIMARY KEY,
      nombre VARCHAR(200)*/
    @Id //para defiir primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
}
