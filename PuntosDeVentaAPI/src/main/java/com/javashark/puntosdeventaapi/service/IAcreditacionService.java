package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.exception.PuntoVentaNotFoundException;
import com.javashark.puntosdeventaapi.model.Acreditacion;

public interface IAcreditacionService {

    public Acreditacion agregarAcreditacion(Acreditacion acreditacion) throws PuntoVentaNotFoundException;

    public Acreditacion obtenerAcreditacionPorId(Long id);
}
