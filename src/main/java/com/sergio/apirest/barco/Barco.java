package com.sergio.apirest.barco;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sergio.apirest.socio.Socio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sergio.apirest.salida.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Barco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String matricula;

    private String nombre;

    private Integer numeroAmarre;


    private Double cuotaAmarre;

    @ManyToOne //muchos barcos pueden estar asociados a un solo socio
    @JoinColumn(name = "socio_id", nullable = true)// Define la columna de la llave foránea que conecta con Socio.nullable = true
    @JsonIgnore // Indica a Jackson que ignore este campo durante la serialización para evitar referencias cíclicas.
    private Socio socio;

    @OneToMany(mappedBy = "barco")// Indica una relación uno a muchos con la entidad Salida (un barco muchas salidas).
    @JsonManagedReference // Arregla el tema de los bucles infinitos
    private Set<Salida> salidas;

}