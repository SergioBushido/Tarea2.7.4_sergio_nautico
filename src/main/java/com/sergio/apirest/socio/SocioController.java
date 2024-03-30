package com.sergio.apirest.socio;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/socio")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;


    //Crear
    @PostMapping
    public void createSocio(@RequestBody Socio socio){

        socioService.createSocio(socio);;

    }

    //Mostrar
   @GetMapping("/{id}")
    public Socio getSocioById(@PathVariable Integer id) {
        return socioService.getSocioById(id); 
    }

    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Socio> updateSocio(@PathVariable Integer id, @RequestBody Socio personDetails) {
        Socio updatedSocio = socioService.updateSocio(id, personDetails);
        return new ResponseEntity<>(updatedSocio, HttpStatus.OK);
    }

    //Metodo para eliminar por id
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocio(@PathVariable Integer id) {
        socioService.deleteSocioById(id);
        return ResponseEntity.noContent().build(); // Devuelve una respuesta 204 No Content
    }
}
