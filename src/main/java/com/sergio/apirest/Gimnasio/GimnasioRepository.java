package com.sergio.apirest.Gimnasio;

import com.sergio.apirest.Natacion.Natacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GimnasioRepository extends JpaRepository<Gimnasio, Long> {
    Optional<Gimnasio> findByDateAndHour(String date, String hour);
    List<Gimnasio> findByDate(String date);
}
