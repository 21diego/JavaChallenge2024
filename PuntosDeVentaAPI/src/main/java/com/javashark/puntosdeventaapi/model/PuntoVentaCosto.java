package com.javashark.puntosdeventaapi.model;

public class PuntoVentaCosto {

    private Long idA;
    private Long idB;
    private int costo;

    public PuntoVentaCosto(Long idA, Long idB, int costo) {
        this.idA = idA;
        this.idB = idB;
        this.costo = costo;
    }

    public Long getIdA() {
        return idA;
    }

    public Long getIdB() {
        return idB;
    }

    public int getCosto() {
        return costo;
    }

    public void setIdA(Long idA) {
        this.idA = idA;
    }

    public void setIdB(Long idB) {
        this.idB = idB;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
