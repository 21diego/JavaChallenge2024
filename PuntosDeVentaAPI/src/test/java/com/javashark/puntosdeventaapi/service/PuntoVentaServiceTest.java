package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.exception.PuntoVentaDuplicatedException;
import com.javashark.puntosdeventaapi.exception.PuntoVentaNotFoundException;
import com.javashark.puntosdeventaapi.model.PuntoVenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PuntoVentaServiceTest {

    @InjectMocks
    private PuntoVentaServiceImpl puntoVentaService;

    @Test
    public void testObtenerTodosLosPuntos(){
        List<PuntoVenta> puntos = puntoVentaService.obtenerTodosLosPuntosVenta();

        assertEquals(10, puntos.size());
        assertTrue(!puntos.isEmpty());
    }

    @Test
    public void testObtenerPuntoPorId(){
        PuntoVenta punto = puntoVentaService.obtenerPorId(1L);

        assertNotNull(punto);
        assertEquals(1L, punto.getId());
    }

    @Test
    public void testObtenerPorIdRetornaException(){
        assertThrows(PuntoVentaNotFoundException.class, () -> puntoVentaService.obtenerPorId(20L));
    }

    @Test
    public void testAgregarPuntoVenta(){
        PuntoVenta punto = new PuntoVenta(11L, "Laferrere");

        puntoVentaService.agregarPuntoVenta(punto);

        assertEquals(11, puntoVentaService.obtenerTodosLosPuntosVenta().size());
        assertEquals("Laferrere", puntoVentaService.obtenerPorId(punto.getId()).getNombre());
    }

    @Test
    public void testAgregarPuntoRetornaException(){
        PuntoVenta punto = new PuntoVenta(10L, "Laferrere");

        assertThrows(PuntoVentaDuplicatedException.class, () -> puntoVentaService.agregarPuntoVenta(punto));
    }

    @Test
    public void testActualizarPuntoVenta(){
        PuntoVenta punto = new PuntoVenta(10L, "Laferrere");
        puntoVentaService.actualizarPuntoVenta(punto);

        assertEquals("Laferrere", puntoVentaService.obtenerPorId(10L).getNombre());
    }

    @Test
    public void testActualizarPuntoRetornaException(){
        PuntoVenta punto = new PuntoVenta(11L, "Laferrere");

        assertThrows(PuntoVentaNotFoundException.class, () -> puntoVentaService.actualizarPuntoVenta(punto));
    }

    @Test
    public void testEliminarPuntoVenta(){
        puntoVentaService.eliminarPuntoVenta(1L);

        assertThrows(PuntoVentaNotFoundException.class, () -> puntoVentaService.obtenerPorId(1L));
        assertEquals(9, puntoVentaService.obtenerTodosLosPuntosVenta().size());
    }

    @Test
    public void testEliminarPuntoRetornaException(){

        assertThrows(PuntoVentaNotFoundException.class, () -> puntoVentaService.eliminarPuntoVenta(12L));
        assertEquals(10, puntoVentaService.obtenerTodosLosPuntosVenta().size());
    }

}
