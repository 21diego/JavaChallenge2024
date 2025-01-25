package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoDuplicatedException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoNotFoundException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoPathNotFoundException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoSelfReferenceException;
import com.javashark.puntosdeventaapi.model.PuntoVenta;
import com.javashark.puntosdeventaapi.model.PuntoVentaCosto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.javashark.puntosdeventaapi.utilities.DijkstraAlgorithm.dijkstra;

@Service
public class PuntoVentaCostoServiceImpl implements IPuntoVentaCostoService {

    private ConcurrentMap<String, PuntoVentaCosto> cache = new ConcurrentHashMap<String, PuntoVentaCosto>();

    public PuntoVentaCostoServiceImpl() {
        cache.put("1-2", new PuntoVentaCosto(1L, 2L, 2));
        cache.put("1-3", new PuntoVentaCosto(1L, 3L, 3));
        cache.put("2-3", new PuntoVentaCosto(2L, 3L, 5));
        cache.put("2-4", new PuntoVentaCosto(2L, 4L, 10));
        cache.put("1-4", new PuntoVentaCosto(1L, 4L, 11));
        cache.put("4-5", new PuntoVentaCosto(4L, 5L, 5));
        cache.put("2-5", new PuntoVentaCosto(2L, 5L, 14));
        cache.put("6-7", new PuntoVentaCosto(6L, 7L, 32));
        cache.put("8-9", new PuntoVentaCosto(8L, 9L, 11));
        cache.put("10-7", new PuntoVentaCosto(10L, 7L, 5));
        cache.put("3-8", new PuntoVentaCosto(3L, 8L, 10));
        cache.put("5-8", new PuntoVentaCosto(5L, 8L, 30));
        cache.put("10-5", new PuntoVentaCosto(10L, 5L, 5));
        cache.put("4-6", new PuntoVentaCosto(4L, 6L, 6));
    }

    public void agregarCosto(PuntoVentaCosto costo){
        Long puntoA = costo.getIdA();
        Long puntoB = costo.getIdB();

        String key = puntoA < puntoB ? puntoA + "-" + puntoB : puntoB + "-" + puntoA;
        if(cache.containsKey(key)){
            PuntoVentaCosto punto = cache.get(key);
            throw new PuntoVentaCostoDuplicatedException("El costo para el camino entre el punto: " + puntoA + " y el punto: " + puntoB + " ya se encuentra seteado y es: " + punto.getCosto() );
        }
        if(puntoA.equals(puntoB)){
            throw new PuntoVentaCostoSelfReferenceException("El punto A y el punto B son el mismo punto, El costo es 0.");
        }
        cache.put(key, costo);
    }

    public void eliminarCosto(Long puntoA, Long puntoB) {
        String key = puntoA < puntoB ? puntoA + "-" + puntoB : puntoB + "-" + puntoA;
        if(!cache.containsKey(key)){
            throw new PuntoVentaCostoNotFoundException("El costo entre el punto A: " + puntoA + " y el punto B: " + puntoB + " no esta seteado.");
        }
        cache.remove(key);
    }

    public List<PuntoVentaCosto> obtenerCostosDelPuntoA(Long puntoA) {
        List<PuntoVentaCosto> costos = new ArrayList<PuntoVentaCosto>();
        for(PuntoVentaCosto costo : cache.values()){
            if(costo.getIdA().equals(puntoA) || costo.getIdB().equals(puntoA)){
                costos.add(costo);
            }
        }
        return costos;
    }

    public Map<String, Object> obtenerCaminoMinimo(Long puntoA, Long puntoB){
        Map<String, Object> resultado = dijkstra(puntoA, puntoB, cache);
        if(resultado.get("cost").equals(-1)){
            throw new PuntoVentaCostoPathNotFoundException("No hay conexion entre el punto A: " + puntoA + " y el punto B: " + puntoB);
        }
        return resultado;
    }

    public List<PuntoVentaCosto> obtenerTodosLosCostos() {
        return new ArrayList<>(cache.values());
    }

    public PuntoVentaCosto obtenerCosto(Long puntoA, Long puntoB) {
        String key = puntoA < puntoB ? puntoA + "-" + puntoB : puntoB + "-" + puntoA;
        if(puntoA.equals(puntoB)){
            return new PuntoVentaCosto(puntoA, puntoB, 0);
        }
        if(!cache.containsKey(key)){
            throw new PuntoVentaCostoNotFoundException("El costo entre el punto A: " + puntoA + " y el punto B: " + puntoB + " no esta seteado.");
        }
        return cache.get(key);
    }
}
