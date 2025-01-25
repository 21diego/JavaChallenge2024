package com.javashark.puntosdeventaapi.exception;

public class PuntoVentaCostoSelfReferenceException extends RuntimeException{

    public PuntoVentaCostoSelfReferenceException(String mensaje) {
        super(mensaje);
    }
}
