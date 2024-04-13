package com.sergio.apirest.salida;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SalidaControllerTest {

    @Mock
    private SalidaService salidaService;

    @InjectMocks
    private SalidaController salidaController;

    @Test
    void should_return_all_salidas() {
        // Given: Configuramos el comportamiento esperado del servicio
        Salida salida1 = new Salida(); // Asume que Salida es una clase con setters para sus campos
        Salida salida2 = new Salida();
        List<Salida> todasLasSalidas = Arrays.asList(salida1, salida2);
        when(salidaService.findAll()).thenReturn(todasLasSalidas);

        // When: Invocamos el método del controlador
        ResponseEntity<List<Salida>> respuesta = salidaController.getAllSalidas();

        // Then: Verificamos que el servicio se invocó y que la respuesta es correcta
        verify(salidaService).findAll(); // Verificamos que se llamó al método findAll del servicio
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); // El estado HTTP debe ser 200 OK
        assertEquals(2, respuesta.getBody().size()); // Debería haber dos salidas en la respuesta
    }

    @Test
    void should_return_salida_by_id_when_found() {
        // Given: Configuramos el comportamiento esperado del servicio para un ID específico
        Integer salidaId = 1;
        Salida expectedSalida = new Salida();
        expectedSalida.setId(salidaId);
        when(salidaService.findById(salidaId)).thenReturn(Optional.of(expectedSalida));

        // When: Invocamos el método del controlador con el ID
        ResponseEntity<Salida> respuesta = salidaController.getSalidaById(salidaId);

        // Then: Verificamos la interacción con el servicio y la respuesta del controlador
        verify(salidaService).findById(salidaId); // Confirma que se llamó al servicio con el ID correcto
        assertEquals(expectedSalida, respuesta.getBody()); // La salida en la respuesta debe ser la esperada
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); // El estado HTTP debe ser 200 OK
    }

    @Test
    void should_delete_salida_when_exists() {
        // Given: Configuramos el servicio para indicar que la salida con el ID dado existe
        Integer salidaId = 1;
        when(salidaService.existsById(salidaId)).thenReturn(true);

        // When: Invocamos el método del controlador para eliminar la salida
        ResponseEntity<Void> respuesta = salidaController.deleteSalida(salidaId);

        // Then: Verificamos que se invocó al servicio y que la respuesta indica éxito
        verify(salidaService).existsById(salidaId); // Verifica que se comprobó la existencia de la salida
        verify(salidaService).deleteById(salidaId); // Confirma que se solicitó eliminar la salida
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); // El estado HTTP debe ser 200 OK indicando éxito
    }


}
