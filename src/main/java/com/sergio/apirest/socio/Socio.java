package com.sergio.apirest.socio;

import java.util.Set;

import com.sergio.apirest.barco.*;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Socio {
    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    private String nombre;
    private String apellidos;
    @OneToMany(mappedBy = "socio")
    private Set<Barco> barcos;


}
