package com.sergio.apirest.barco;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcoRepository extends JpaRepository<Barco, Integer> {
    List<Barco> findByNombreContainingIgnoreCase(String nombre);
    Optional<Barco> findByNumeroAmarre(Integer numeroAmarre);
}