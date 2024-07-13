package com.sergio.apirest.tenis;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sergio.apirest.Reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String hour;
    private int availableSeats;

    @OneToMany(mappedBy = "tenis", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "tenis-reservations")

    private List<Reservation> reservations;

}
