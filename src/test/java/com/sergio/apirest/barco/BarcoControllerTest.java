package com.sergio.apirest.barco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BarcoControllerTest {

    @Mock
    private BarcoService barcoService;

    @InjectMocks
    private BarcoController barcoController;

    @Test
    void should_cuota_total_amarre(){

        // Given
        // When
        barcoController.getTotalCuotaAmarre();

        // Then
        verify(barcoService).getTotalCuotaAmarre();
    }


    @Test
    void should_buscar_barco_por_id_when_no_encuentra() {
        // Given
        Integer barcoId = 1;
        Barco expectedBarco = new Barco();
        expectedBarco.setId(barcoId);
        expectedBarco.setNombre("Barco Test");
        when(barcoService.findById(barcoId)).thenReturn(Optional.of(expectedBarco));

        // When
        ResponseEntity<Barco> response = barcoController.getBarcoById(barcoId);

        // Then
        verify(barcoService).findById(barcoId);
        assertEquals(expectedBarco, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void should_buscar_barco_por_id_when_si_encuentra() {
        // Given
        Integer barcoId = 1;
        when(barcoService.findById(barcoId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Barco> response = barcoController.getBarcoById(barcoId);

        // Then
        verify(barcoService).findById(barcoId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
