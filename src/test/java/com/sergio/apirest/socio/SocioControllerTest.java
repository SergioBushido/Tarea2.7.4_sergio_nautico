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






}
