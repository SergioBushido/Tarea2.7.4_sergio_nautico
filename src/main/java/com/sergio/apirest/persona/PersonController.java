package com.sergio.apirest.persona;

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
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


    //Crear
    @PostMapping
    public void createPersona(@RequestBody Person person){

        personService.createPersona(person);

    }

    //Mostrar
   @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id); 
    }

    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person personDetails) {
        Person updatedPerson = personService.updatePerson(id, personDetails);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    //Metodo para eliminar por id
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build(); // Devuelve una respuesta 204 No Content
    }
}
