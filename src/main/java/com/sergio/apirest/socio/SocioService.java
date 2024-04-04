package com.sergio.apirest.socio;


import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepo;

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
}