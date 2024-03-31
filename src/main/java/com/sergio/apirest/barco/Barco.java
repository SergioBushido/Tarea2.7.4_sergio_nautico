package com.sergio.apirest.barco;

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
public class Barco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "La matrícula no puede estar vacía")
    private String matricula;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El número de amarre es obligatorio")
    private Integer numeroAmarre;

    @NotNull(message = "La cuota de amarre es obligatoria")
    @Positive(message = "La cuota de amarre debe ser un número positivo")
    private Double cuotaAmarre;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    @JsonIgnore
    private Socio socio;

    @OneToMany(mappedBy = "barco")
    private Set<Salida> salidas;
}