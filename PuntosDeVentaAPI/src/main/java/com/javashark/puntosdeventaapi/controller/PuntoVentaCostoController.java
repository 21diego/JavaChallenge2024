package com.javashark.puntosdeventaapi.controller;

import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoDuplicatedException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoNotFoundException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoPathNotFoundException;
import com.javashark.puntosdeventaapi.model.PuntoVentaCosto;
import com.javashark.puntosdeventaapi.service.IPuntoVentaCostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/costos")
public class PuntoVentaCostoController {

    @Autowired
    IPuntoVentaCostoService costoService;

    @PostMapping
    public ResponseEntity<Object> agregarCosto(@RequestBody PuntoVentaCosto costo) {
        try{
            costoService.agregarCosto(costo);
            return ResponseEntity.status(HttpStatus.CREATED).body(costo);
        }catch(PuntoVentaCostoDuplicatedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<Object> eliminarCosto(@RequestParam Long idA, @RequestParam Long idB) {
        try {
            costoService.eliminarCosto(idA, idB);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(PuntoVentaCostoNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerCostosDePuntoA(@PathVariable Long id) {
        List<PuntoVentaCosto> costosDeA = costoService.obtenerCostosDelPuntoA(id);
        if(costosDeA.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("No hay caminos directos al punto: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(costosDeA);
    }

    @GetMapping("/minimo")
    public ResponseEntity<Object> obtenerCaminoMinimo(@RequestParam Long idA, @RequestParam Long idB) {
        try {
            Map<String, Object> resultado = costoService.obtenerCaminoMinimo(idA, idB);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        }catch(PuntoVentaCostoPathNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
