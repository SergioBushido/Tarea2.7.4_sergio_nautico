package com.sergio.apirest.salida;

import com.sergio.apirest.patron.Patron;
import com.sergio.apirest.patron.PatronRepository;
import com.sergio.apirest.salida.dto.SalidaRequestMapper;
import com.sergio.apirest.salida.dto.SalidaResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalidaService {

    private final SalidaRepository salidaRepository;
    private final PatronRepository patronRepository;
    private final SalidaRequestMapper salidaRequestMapper;
    private final SalidaResponseMapper salidaResponseMapper;


    public List<Salida> findAll() {
        return salidaRepository.findAll();
    }

    public Optional<Salida> findById(Integer id) {
        return salidaRepository.findById(id);
    }

    @Transactional
    public Salida save(Salida salida) {
        // Validar y/o buscar el Patron antes de asignarlo a la Salida
        Patron patron = patronRepository.findById(salida.getPatron().getId())
                .orElseThrow(() -> new RuntimeException("Patron not found"));
        salida.setPatron(patron);

        return salidaRepository.save(salida);
    }

    @Transactional
    public Optional<Salida> update(Integer id, Salida salidaDetails) {
        return salidaRepository.findById(id).map(salida -> {
            salida.setFechaHoraSalida(salidaDetails.getFechaHoraSalida());
            salida.setDestino(salidaDetails.getDestino());
            salida.setBarco(salidaDetails.getBarco());

            // Actualizar Patron
            Patron patron = patronRepository.findById(salidaDetails.getPatron().getId())
                    .orElseThrow(() -> new RuntimeException("Patron not found"));
            salida.setPatron(patron);

            return salidaRepository.save(salida);
        });
    }

    @Transactional
    public void deleteById(Integer id) {
        salidaRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return salidaRepository.existsById(id);
    }
}
