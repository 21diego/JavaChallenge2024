package com.javashark.puntosdeventaapi.service;

import com.javashark.puntosdeventaapi.exception.PuntoVentaNotFoundException;
import com.javashark.puntosdeventaapi.model.Acreditacion;
import com.javashark.puntosdeventaapi.model.PuntoVenta;
import com.javashark.puntosdeventaapi.repository.AcreditacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AcreditacionServiceTest {

    @Mock
    private AcreditacionRepository acreditacionRepository;

    @Mock
    private PuntoVentaServiceImpl puntoVentaService;

    @InjectMocks
    private AcreditacionServiceImpl acreditacionService;

    @Test
    void testAgregarAcreditacion() {

        PuntoVenta puntoVenta = new PuntoVenta(2L,"CABA");
        when(puntoVentaService.obtenerPorId(2L)).thenReturn(puntoVenta);

        Acreditacion acreditacion = new Acreditacion();
        acreditacion.setImporte(2500L);
        acreditacion.setIdPuntoVenta(2L);

        when(acreditacionRepository.save(any(Acreditacion.class))).thenReturn(acreditacion);

        Acreditacion resultado = acreditacionService.agregarAcreditacion(acreditacion);

        assertNotNull(resultado);
        assertEquals(acreditacion.getIdPuntoVenta(), resultado.getIdPuntoVenta());
        assertEquals("CABA", resultado.getNombrePuntoVenta());
    }

    @Test
    void testAgregarAcreditacionRetornaException() {

        when(puntoVentaService.obtenerPorId(2L)).thenThrow(PuntoVentaNotFoundException.class);

        Acreditacion acreditacion = new Acreditacion();
        acreditacion.setImporte(2500L);
        acreditacion.setIdPuntoVenta(2L);

        assertThrows(PuntoVentaNotFoundException.class, () -> {acreditacionService.agregarAcreditacion(acreditacion);});
    }

    @Test
    void testObtenerAcreditacionPorId() {

        Acreditacion acreditacion = new Acreditacion();
        acreditacion.setImporte(2500L);
        acreditacion.setIdPuntoVenta(2L);
        acreditacion.setId(1L);

        when(acreditacionRepository.findById(1L)).thenReturn(Optional.of(acreditacion));

        Acreditacion resultado = acreditacionService.obtenerAcreditacionPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(2500L, resultado.getImporte());
        assertEquals(2L, resultado.getIdPuntoVenta());

    }

    @Test
    void testObtenerAcreditacionRetornaException() {

    }


}
