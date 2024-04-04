package com.sergio.apirest.barco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

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
}
