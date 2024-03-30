package com.sergio.apirest.salida;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalidaService {

    private final SalidaRepository salidaRepository;

    public List<Salida> findAll() {
        return salidaRepository.findAll();
    }

    public Optional<Salida> findById(Integer id) {
        return salidaRepository.findById(id);
    }

    @Transactional
    public Salida save(Salida salida) {
        // Aquí puede ir lógica antes de guardar la salida, como validaciones o transformaciones
        return salidaRepository.save(salida);
    }

    @Transactional
    public Optional<Salida> update(Integer id, Salida salidaDetails) {
        return salidaRepository.findById(id)
            .map(salida -> {
                // Aquí se actualizan los campos de la salida existente con los detalles proporcionados
                salida.setFechaHoraSalida(salidaDetails.getFechaHoraSalida());
                salida.setDestino(salidaDetails.getDestino());
                salida.setBarco(salidaDetails.getBarco());
                salida.setDatosPatron(salidaDetails.getDatosPatron());
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
