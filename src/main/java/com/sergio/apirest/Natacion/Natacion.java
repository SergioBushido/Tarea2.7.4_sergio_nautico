package com.sergio.apirest.Natacion;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sergio.apirest.Reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Natacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String hour;
    private int availableSeats;

    @OneToMany(mappedBy = "natacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "natacion-reservations")

    private List<Reservation> reservations;

   /* //relacion de natacion con reservations
    @OneToMany(mappedBy = "natacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "natacion-reservations")
    private List<Reservation> reservations = new ArrayList<>();*/
}
