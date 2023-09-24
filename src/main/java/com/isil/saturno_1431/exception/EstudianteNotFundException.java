package com.isil.saturno_1431.exception;

public class EstudianteNotFundException extends RuntimeException{
    public EstudianteNotFundException(int id){
        super(String.format("Estudiante con id : %s no registrado en la base de datos", id));
    }
}
