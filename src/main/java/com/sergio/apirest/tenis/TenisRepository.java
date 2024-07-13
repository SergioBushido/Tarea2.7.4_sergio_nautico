package com.sergio.apirest.tenis;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenisRepository extends JpaRepository<Tenis, Long> {
    Optional<Tenis> findByDateAndHour(String date, String hour);
    List<Tenis> findByDate(String date);
}
