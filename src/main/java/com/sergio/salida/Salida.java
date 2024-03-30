package com.sergio.salida;

import java.time.LocalDateTime;

import com.sergio.barco.Barco;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public
class Salida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fechaHoraSalida;
    private String destino;

    @ManyToOne
    @JoinColumn(name = "barco_id")
    private Barco barco;

    @Basic
    private String datosPatron; 
}