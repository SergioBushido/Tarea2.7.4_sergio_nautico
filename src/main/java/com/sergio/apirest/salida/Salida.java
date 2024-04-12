package com.sergio.apirest.salida;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sergio.apirest.barco.Barco;
import com.sergio.apirest.patron.Patron;
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
//Esta técnica previene la recursión infinita y hace que la salida JSON sea más legible y manejable, especialmente cuando estás trabajando con estructuras de datos complejas que involucran relaciones bidireccionales o grafos de objetos.
public class Salida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha y hora de salida son obligatorias")
    @FutureOrPresent(message = "La fecha y hora de salida deben ser en el presente o en el futuro")
    private LocalDateTime fechaHoraSalida;

    @NotBlank(message = "El destino no puede estar vacío")
    private String destino;

    @ManyToOne // muchas salidas a un solo barco.
    @JoinColumn(name = "barco_id") // Define la columna de clave foránea 'barco_id' en la tabla de esta entidad para establecer la relación con la tabla de Barco.
    @JsonBackReference // Previene problemas de referencia circular en la serialización JSON.
    private Barco barco;

    @ManyToOne // Puede haber muchos patrones para un solo barco.
    @JoinColumn(name = "patron_id") // Establece la columna de clave foránea 'patron_id' para enlazar con la tabla de Patron.
    private Patron patron; // La propiedad 'patron' mantiene la referencia al Patron asociado.


}