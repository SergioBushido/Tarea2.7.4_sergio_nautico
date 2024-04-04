package com.sergio.apirest.socio;


import com.sergio.apirest.barco.Barco;
import com.sergio.apirest.barco.BarcoRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepo;
    private final BarcoRepository barcoRepository;


    // Insertar
    @Transactional
    public Socio createSocio(Socio socio) {
        socio.getBarcos().forEach(barco -> barco.setSocio(socio));
        return socioRepo.save(socio);
    }


    //Mostrar por id
    public Socio getSocioById(Integer id) {
        return socioRepo.findById(id).orElse(null);
    }

    //Actualizar
    @Transactional
    public Socio updateSocio(Integer id, Socio socioDetails) {
        // Encuentra la persona existente o arroja una excepción
        Socio socio = socioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Socio not found with id: " + id));

        // Actualiza los datos con la información del objeto 'personDetails'
        socio.setNombre(socioDetails.getNombre());
        socio.setApellidos(socioDetails.getApellidos());


        // Guarda la persona actualizada en la base de datos
        return socioRepo.save(socio);
    }

    //Eliminar
    @Transactional
    public void deleteSocioById(Integer id) {
        // Verifica si existe la persona con el ID proporcionado para evitar la excepción EmptyResultDataAccessException
        if (socioRepo.existsById(id)) {
            socioRepo.deleteById(id);
        } else {
            throw new RuntimeException("Socio not found with id: " + id);
        }
    }

    // estou intentando meter un barco a un socio por su id
    @Transactional
    public Barco addBarcoToSocio(Integer socioId, Barco newBarco) {
        // Buscar al socio por ID
        Optional<Socio> socioOptional = socioRepo.findById(socioId);
        if (!socioOptional.isPresent()) {
            throw new RuntimeException("Socio not found with id: " + socioId);
        }
        Socio socio = socioOptional.get();

        // Asignar el socio al nuevo barco
        newBarco.setSocio(socio);

        // Guardar el nuevo barco
        Barco savedBarco = barcoRepository.save(newBarco);

        // Añadir el nuevo barco a la lista de barcos del socio
        socio.getBarcos().add(savedBarco);

        // Guardar el socio con el nuevo barco añadido
        socioRepo.save(socio);

        return savedBarco;
    }



}