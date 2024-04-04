package com.sergio.apirest.salida;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sergio.apirest.barco.Barco;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Salida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha y hora de salida son obligatorias")
    @FutureOrPresent(message = "La fecha y hora de salida deben ser en el presente o en el futuro")
    private LocalDateTime fechaHoraSalida;

    @NotBlank(message = "El destino no puede estar vacío")
    private String destino;

    @ManyToOne
    @JoinColumn(name = "barco_id")
    @JsonBackReference
    private Barco barco;



    private String datosPatron;
}