package com.sergio.apirest.Natacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NatacionRepository extends JpaRepository<Natacion, Long> {
    Optional<Natacion> findByDateAndHour(String date, String hour);
    List<Natacion> findByDate(String date);
}
