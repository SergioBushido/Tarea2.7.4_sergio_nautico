package com.sergio.apirest.persona;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepo;

    //insertar
    public void createPersona(Person person){

        personRepo.save(person);

    }


    //Mostrar por id
     public Person getPersonById(Integer id) {
        return personRepo.findById(id).orElse(null); 
    }

    //Actualizar
    @Transactional
    public Person updatePerson(Integer id, Person personDetails) {
        // Encuentra la persona existente o arroja una excepción
        Person person = personRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
        
        // Actualiza los datos con la información del objeto 'personDetails'
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setEmail(personDetails.getEmail());
        
        // Guarda la persona actualizada en la base de datos
        return personRepo.save(person);
    }

    //Eliminar
    @Transactional
    public void deletePersonById(Integer id) {
        // Verifica si existe la persona con el ID proporcionado para evitar la excepción EmptyResultDataAccessException
        if (personRepo.existsById(id)) {
            personRepo.deleteById(id);
        } else {
            throw new RuntimeException("Person not found with id: " + id);
        }
    }
}
