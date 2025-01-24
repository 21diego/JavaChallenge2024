package com.javashark.puntosdeventaapi.exception;

public class PuntoVentaDuplicatedException extends RuntimeException{

    public PuntoVentaDuplicatedException(String mensaje){
        super(mensaje);
    }
}
