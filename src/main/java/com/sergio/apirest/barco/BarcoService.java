package com.sergio.apirest.barco;

import com.sergio.apirest.barco.dto.BarcoRequest;
import com.sergio.apirest.barco.dto.BarcoRequestMapper;
import com.sergio.apirest.barco.dto.BarcoResponse;
import com.sergio.apirest.barco.dto.BarcoResponseMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BarcoService {

    private final BarcoRepository barcoRepository;
    private final BarcoRequestMapper barcoRequestMapper;
    private final BarcoResponseMapper barcoResponseMapper;

    public List<BarcoResponse> findAll() {
        return barcoResponseMapper.entitiesToResponses(barcoRepository.findAll());
    }

    public Optional<BarcoResponse> findById(final Integer id) {
        final Optional<Barco> barcoOptional = barcoRepository.findById(id);
        return barcoOptional.map(barcoResponseMapper::entityToResponse);
    }

    @Transactional
    public BarcoResponse save(final BarcoRequest barcoRequest) {
        Barco barco = barcoRequestMapper.dtoToEntity(barcoRequest);
        barco = barcoRepository.save(barco);
        return barcoResponseMapper.entityToResponse(barco);
    }

    //actualiza barco
    @Transactional
    public Optional<BarcoResponse> update(final Integer id, final BarcoRequest barcoDetails) {
        final Optional<Barco> existingBarco = barcoRepository.findById(id);
        existingBarco.ifPresent(barco -> {
            barcoResponseMapper.updateEntityFromDto(barcoDetails, barco);
            barcoRepository.save(barco);
        });
        return existingBarco.map(barcoResponseMapper::entityToResponse);
    }

    //elimina barco
    public void deleteById(final Integer id) {
        barcoRepository.deleteById(id);
    }

    public boolean existsById(final Integer id) {
        return barcoRepository.existsById(id);
    }

    // 1. Buscar barcos por nombre
    public List<BarcoResponse> findByNombre(final String nombre) {
        final var barcos = barcoRepository.findByNombreContainingIgnoreCase(nombre);
        return barcoResponseMapper.entitiesToResponses(barcos);
    }

    // 2. Obtener la cuota total de amarre
    public Double getTotalCuotaAmarre() {
        return barcoRepository.findAll().stream()
                .mapToDouble(Barco::getCuotaAmarre)
                .sum();
    }

    // 3. Actualizar la cuota de amarre por número de amarre
    @Transactional
    public Optional<BarcoResponse> updateCuotaAmarreByNumeroAmarre(final Integer numeroAmarre, final Double nuevaCuota) {
        final Optional<Barco> existingBarco = barcoRepository.findByNumeroAmarre(numeroAmarre);
        existingBarco.ifPresent(barco -> {
            barco.setCuotaAmarre(nuevaCuota);
            barcoRepository.save(barco);
        });
        return existingBarco.map(barcoResponseMapper::entityToResponse);
    }

}