package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.exception.AcreditacionNotFoundException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaNotFoundException;
import com.javashark.puntosdeventaapi.model.Acreditacion;
import com.javashark.puntosdeventaapi.model.PuntoVenta;
import com.javashark.puntosdeventaapi.repository.AcreditacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class AcreditacionServiceImpl implements IAcreditacionService {

    @Autowired
    AcreditacionRepository acreditacionRepository;

    @Autowired
    PuntoVentaServiceImpl puntoVentaService;

    public Acreditacion agregarAcreditacion(Acreditacion acreditacion) throws PuntoVentaNotFoundException {

        PuntoVenta punto = puntoVentaService.obtenerPorId(acreditacion.getIdPuntoVenta());
        acreditacion.setNombrePuntoVenta(punto.getNombre());
        acreditacion.setFechaRecepcion(new Date());
        return acreditacionRepository.save(acreditacion);
    }

    public Acreditacion obtenerAcreditacionPorId(Long id) {
        try {
            Acreditacion acreditacion = acreditacionRepository.findById(id).get();
            return acreditacion;
        } catch (NoSuchElementException e){
            throw new AcreditacionNotFoundException("La acreditacion no existe");
        }
    }


}
