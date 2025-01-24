package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.exception.PuntoVentaDuplicatedException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaNotFoundException;
import com.javashark.puntosdeventaapi.model.PuntoVenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class PuntoVentaServiceImpl implements IPuntoVentaService {

    // Voy a usar esta estructura para contemplar casos de concurrencia
    // Utilizo un Map para que las operaciones puedan ser ejecutadas rapidamente
    private final ConcurrentMap<Long, PuntoVenta> cache = new ConcurrentHashMap<Long, PuntoVenta>();

    public PuntoVentaServiceImpl() {
        // Cargo datos inciales
        cache.put(1L, new PuntoVenta(1L, "CABA"));
        cache.put(2L, new PuntoVenta(2L, "GBA_1"));
        cache.put(3L, new PuntoVenta(3L, "GBA_2"));
        cache.put(4L, new PuntoVenta(4L, "Santa Fe"));
        cache.put(5L, new PuntoVenta(5L, "Córdoba"));
        cache.put(6L, new PuntoVenta(6L, "Misiones"));
        cache.put(7L, new PuntoVenta(7L, "Salta"));
        cache.put(8L, new PuntoVenta(8L, "Chubut"));
        cache.put(9L, new PuntoVenta(9L, "Santa Cruz"));
        cache.put(10L, new PuntoVenta(10L, "Catamarca"));
    }


    public List<PuntoVenta> obtenerTodosLosPuntosVenta() {
        return new ArrayList<>(cache.values());
    }

    public PuntoVenta obtenerPorId(Long id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            throw new PuntoVentaNotFoundException("El punto de venta con id " + id + " no existe");
        }
    }

    public void agregarPuntoVenta(PuntoVenta puntoVenta) {
        if(cache.containsKey(puntoVenta.getId())) {
            throw new PuntoVentaDuplicatedException("El punto ya existe en la cache");
        }
        cache.put(puntoVenta.getId(), puntoVenta);
    }

    public void actualizarPuntoVenta(PuntoVenta puntoVenta) {
        if (cache.containsKey(puntoVenta.getId())) {
            cache.put(puntoVenta.getId(), puntoVenta);
        } else {
            throw new PuntoVentaNotFoundException("El punto de venta con id " + puntoVenta.getId() + " no existe");
        }
    }

    public void eliminarPuntoVenta(Long id) {
        if(!cache.containsKey(id)) {
            throw new PuntoVentaNotFoundException("El punto de venta con id " + id + " no existe");
        }
        cache.remove(id);
    }


}
