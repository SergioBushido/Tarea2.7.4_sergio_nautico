package com.sergio.apirest.socio;

import com.sergio.apirest.barco.Barco;
import com.sergio.apirest.barco.BarcoController;
import com.sergio.apirest.barco.BarcoService;
import com.sergio.apirest.salida.SalidaController;
import com.sergio.apirest.salida.SalidaService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SocioControllerTest {

    @Mock
    private SocioService socioService;

    @InjectMocks
    private SocioController socioController;

    @Test
    void should_create_socio() {
        // Given: Preparamos los datos de entrada y la respuesta esperada del servicio
        Socio nuevoSocio = new Socio(); // Asume que Socio es una clase con setters para sus campos
        Socio socioCreado = new Socio();
        when(socioService.createSocio(any(Socio.class))).thenReturn(socioCreado);

        // When: Invocamos el método del controlador para crear un nuevo socio
        ResponseEntity<Socio> respuesta = socioController.createSocio(nuevoSocio);

        // Then: Verificamos que el servicio fue invocado correctamente y que la respuesta es la esperada
        verify(socioService).createSocio(nuevoSocio); // Verifica que se llamó al servicio con el socio correcto
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode()); // Verifica que el estado HTTP es 201 CREATED
        assertEquals(socioCreado, respuesta.getBody()); // Verifica que el cuerpo de la respuesta contiene el socio creado
    }

    @Test
    void should_return_socio_by_id_when_exists() {
        // Given: Configuramos el servicio para devolver un socio específico cuando se busque por un ID existente
        Integer socioId = 1;
        Socio expectedSocio = new Socio();
        expectedSocio.setId(socioId);
        when(socioService.getSocioById(socioId)).thenReturn(expectedSocio);

        // When: Invocamos el método del controlador con el ID
        ResponseEntity<Socio> respuesta = socioController.getSocioById(socioId);

        // Then: Verificamos que se invocó al servicio correctamente y que la respuesta es la esperada
        verify(socioService).getSocioById(socioId); // Verifica que se llamó al servicio con el ID correcto
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); // El estado HTTP debe ser 200 OK
        assertEquals(expectedSocio, respuesta.getBody()); // El socio en la respuesta debe ser el esperado
    }


    @Test
    void should_add_barco_to_socio_when_socio_exists() {
        // Given: Configuramos los datos de entrada y el comportamiento esperado del servicio
        Integer socioId = 1;
        Barco newBarco = new Barco(); // Asume que Barco es una clase con setters para sus campos
        Barco addedBarco = new Barco();
        when(socioService.addBarcoToSocio(eq(socioId), any(Barco.class))).thenReturn(addedBarco);

        // When: Invocamos el método del controlador para añadir un barco a un socio
        ResponseEntity<Barco> respuesta = socioController.addBarcoToSocio(socioId, newBarco);

        // Then: Verificamos que el servicio fue invocado correctamente y que la respuesta es la esperada
        verify(socioService).addBarcoToSocio(socioId, newBarco); // Verifica que se llamó al servicio con los argumentos correctos
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode()); // Verifica que el estado HTTP es 201 CREATED
        assertEquals(addedBarco, respuesta.getBody()); // Verifica que el cuerpo de la respuesta contiene el barco añadido
    }



}
