package com.sergio.apirest.barco;

import java.util.Set;

import com.sergio.apirest.socio.Socio;
import com.sergio.apirest.salida.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String matricula;
    private String nombre;
    private Integer numeroAmarre;
    private Double cuotaAmarre;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Socio socio;

    @OneToMany(mappedBy = "barco")
    private Set<Salida> salidas;
}