package com.javashark.puntosdeventaapi.controller;

import com.javashark.puntosdeventaapi.exception.PuntoVentaDuplicatedException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaNotFoundException;
import com.javashark.puntosdeventaapi.model.PuntoVenta;
import com.javashark.puntosdeventaapi.service.IPuntoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntosVenta")
public class PuntoVentaController {

    @Autowired
    IPuntoVentaService puntoVentaService;

    @GetMapping
    public ResponseEntity<List<PuntoVenta>> obtenerTodosPuntosVenta(){
        List<PuntoVenta> puntos =  puntoVentaService.obtenerTodosLosPuntosVenta();
        return ResponseEntity.ok(puntos);
    }

    @PostMapping
    public ResponseEntity<Object> agregarPuntoVenta(@RequestBody PuntoVenta puntoVenta) {
        try {
            puntoVentaService.agregarPuntoVenta(puntoVenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(puntoVenta);

        } catch (PuntoVentaDuplicatedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<Object> actualizarPuntoVenta(@RequestBody PuntoVenta puntoVenta) {
        try {
            puntoVentaService.actualizarPuntoVenta(puntoVenta);
            return ResponseEntity.ok(puntoVenta);
        } catch (PuntoVentaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para eliminar un punto de venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPuntoVenta(@PathVariable Long id) {
        try {
            puntoVentaService.eliminarPuntoVenta(id);
            return ResponseEntity.noContent().build();
        } catch (PuntoVentaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
