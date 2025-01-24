package com.javashark.puntosdeventaapi.controller;

import com.javashark.puntosdeventaapi.exception.AcreditacionNotFoundException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaNotFoundException;
import com.javashark.puntosdeventaapi.model.Acreditacion;
import com.javashark.puntosdeventaapi.service.IAcreditacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acreditacion")
public class AcreditacionController {

    @Autowired
    IAcreditacionService acreditacionService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAcreditacionById(@PathVariable Long id) {
        try{
            Acreditacion acreditacion = acreditacionService.obtenerAcreditacionPorId(id);
            return ResponseEntity.ok(acreditacion);
        }catch (AcreditacionNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> agregarAcreditacion(@RequestBody Acreditacion nuevaAcreditacion) {
        try{
            Acreditacion acreditacion = acreditacionService.agregarAcreditacion(nuevaAcreditacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(acreditacion);
        }catch(PuntoVentaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
