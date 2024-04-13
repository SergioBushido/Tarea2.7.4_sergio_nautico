package com.sergio.apirest.patron;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


public class PatronControllerTest {
    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @Test
    void should_crea_patron() {
        // Given
        Patron nuevoPatron = new Patron();
        nuevoPatron.setNombre("Sergio Fernandez");
        nuevoPatron.setLicencia("ABC123");
        when(patronService.savePatron(any(Patron.class))).thenReturn(nuevoPatron);

        // When
        ResponseEntity<Patron> response = patronController.createPatron(nuevoPatron);

        // Then
        verify(patronService).savePatron(any(Patron.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(nuevoPatron, response.getBody());
    }

    @Test
    void should_busca_patron_por_id() {
        // Given
        Integer patronId = 1;
        Patron expectedPatron = new Patron();
        expectedPatron.setId(patronId);
        expectedPatron.setNombre("Sergio Fernandez");
        when(patronService.findPatronById(patronId)).thenReturn(Optional.of(expectedPatron));

        // When
        ResponseEntity<Patron> response = patronController.getPatronById(patronId);

        // Then
        verify(patronService).findPatronById(patronId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPatron, response.getBody());
    }


    @Test
    void should_elimina_patron() {
        // Given
        Integer patronId = 1;
        willDoNothing().given(patronService).deletePatron(patronId);

        // When
        ResponseEntity<Void> response = patronController.deletePatron(patronId);

        // Then
        verify(patronService).deletePatron(patronId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
