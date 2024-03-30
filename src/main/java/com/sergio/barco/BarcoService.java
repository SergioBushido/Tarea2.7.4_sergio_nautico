package com.sergio.barco;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarcoService {

    private final BarcoRepository barcoRepository;

    public List<Barco> findAll() {
        return barcoRepository.findAll();
    }

    public Optional<Barco> findById(Integer id) {
        return barcoRepository.findById(id);
    }

    public Barco save(Barco barco) {
        return barcoRepository.save(barco);
    }

    @Transactional
    public Optional<Barco> update(Integer id, Barco barcoDetails) {
        return barcoRepository.findById(id)
            .map(barco -> {
                barco.setMatricula(barcoDetails.getMatricula());
                barco.setNombre(barcoDetails.getNombre());
                barco.setNumeroAmarre(barcoDetails.getNumeroAmarre());
                barco.setCuotaAmarre(barcoDetails.getCuotaAmarre());
                return barcoRepository.save(barco);
            });
    }

    public void deleteById(Integer id) {
        barcoRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return barcoRepository.existsById(id);
    }

    // 1. Buscar barcos por nombre
    public List<Barco> findByNombre(String nombre) {
        return barcoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // 2. Obtener la cuota total de amarre
    public Double getTotalCuotaAmarre() {
        return barcoRepository.findAll().stream()
                              .mapToDouble(Barco::getCuotaAmarre)
                              .sum();
    }

    // 3. Actualizar la cuota de amarre por número de amarre
    @Transactional
    public Optional<Barco> updateCuotaAmarreByNumeroAmarre(Integer numeroAmarre, Double nuevaCuota) {
        return barcoRepository.findByNumeroAmarre(numeroAmarre)
            .map(barco -> {
                barco.setCuotaAmarre(nuevaCuota);
                return barcoRepository.save(barco);
            });
    }
    
}
