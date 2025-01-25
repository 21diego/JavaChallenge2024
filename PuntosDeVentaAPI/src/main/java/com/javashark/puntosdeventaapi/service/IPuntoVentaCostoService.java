package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.model.PuntoVentaCosto;

import java.util.List;
import java.util.Map;

public interface IPuntoVentaCostoService {

    void agregarCosto(PuntoVentaCosto costo);

    void eliminarCosto(Long puntoA, Long puntoB);

    List<PuntoVentaCosto> obtenerCostosDelPuntoA(Long puntoA);

    Map<String, Object> obtenerCaminoMinimo(Long puntoA, Long puntoB);

    List<PuntoVentaCosto> obtenerTodosLosCostos();

    PuntoVentaCosto obtenerCosto(Long puntoA, Long puntoB);
}
