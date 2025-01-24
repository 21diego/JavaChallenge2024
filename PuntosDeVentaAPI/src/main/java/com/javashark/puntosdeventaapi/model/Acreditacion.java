package com.javashark.puntosdeventaapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Acreditacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long importe;

    private Long idPuntoVenta;

    private Date fechaRecepcion;

    private String nombrePuntoVenta;

    public Long getId() {
        return id;
    }

    public Long getImporte() {
        return importe;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public Long getIdPuntoVenta() {
        return idPuntoVenta;
    }

    public String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImporte(Long importe) {
        this.importe = importe;
    }

    public void setIdPuntoVenta(Long idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public void setNombrePuntoVenta(String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
    }
}
