package com.javashark.puntosdeventaapi.exception;

public class PuntoVentaNotFoundException extends RuntimeException {

    public PuntoVentaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
