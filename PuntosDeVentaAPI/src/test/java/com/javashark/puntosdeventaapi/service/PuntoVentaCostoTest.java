package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoDuplicatedException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoNotFoundException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaCostoSelfReferenceException;
import com.javashark.puntosdeventaapi.model.PuntoVentaCosto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PuntoVentaCostoTest {

    @InjectMocks
    private PuntoVentaCostoServiceImpl puntoVentaCostoService;

    @Test
    public void testAgregarCosto() {
        PuntoVentaCosto costo = new PuntoVentaCosto(9L, 10L, 14);
        puntoVentaCostoService.agregarCosto(costo);

        assertEquals(15, puntoVentaCostoService.obtenerTodosLosCostos().size());
        assertEquals(14, puntoVentaCostoService.obtenerCosto(9L,10L).getCosto());
    }

    @Test
    public void testAgregarMismoPuntoVentaCostoRertornaException() {

        PuntoVentaCosto costo = new PuntoVentaCosto(9L, 9L, 14);

        assertThrows(PuntoVentaCostoSelfReferenceException.class, () -> puntoVentaCostoService.agregarCosto(costo));
    }

    @Test
    public void testAgregarCostoDuplicadoRetornaException() {
        PuntoVentaCosto costo = new PuntoVentaCosto(1L, 2L, 14);
        assertThrows(PuntoVentaCostoDuplicatedException.class, () -> puntoVentaCostoService.agregarCosto(costo));
    }

    @Test
    public void testMismosPuntosIgualCosto(){
        PuntoVentaCosto costo1 = puntoVentaCostoService.obtenerCosto(2L, 5L);
        PuntoVentaCosto costo2 = puntoVentaCostoService.obtenerCosto(5L, 2L);

        assertEquals(costo1.getCosto(), costo2.getCosto());
    }

    @Test
    public void testEliminarCosto() {
        puntoVentaCostoService.eliminarCosto(8L, 5L);

        assertEquals(13, puntoVentaCostoService.obtenerTodosLosCostos().size());
    }

    @Test
    public void testEliminarCostoRetornaException() {

        assertThrows(PuntoVentaCostoNotFoundException.class, () -> puntoVentaCostoService.eliminarCosto(1L, 5L));
    }

    @Test
    public void testObtenerCostoMismosPuntos(){
        PuntoVentaCosto costo = puntoVentaCostoService.obtenerCosto(2L, 2L);

        assertEquals(0, costo.getCosto());
    }
}
