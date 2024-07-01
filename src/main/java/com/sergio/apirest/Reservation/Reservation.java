package com.sergio.apirest.Reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sergio.apirest.Natacion.Natacion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime hour;
    private String reservationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "natacion_id")
    @JsonBackReference

    private Natacion natacion;
}
