package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.model.PuntoVenta;

import java.util.List;

public interface IPuntoVentaService {

    public List<PuntoVenta> obtenerTodosLosPuntosVenta();

    public PuntoVenta obtenerPorId(Long id);

    public void agregarPuntoVenta(PuntoVenta puntoVenta);

    public void actualizarPuntoVenta(PuntoVenta puntoVenta);

    public void eliminarPuntoVenta(Long id);
}
