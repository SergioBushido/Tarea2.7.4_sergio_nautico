package com.sergio.apirest.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrones") // Asegúrate de que esta ruta coincida con la que intentas acceder
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<Patron> getAllPatrones() {
        return patronService.findAllPatrones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Integer id) {
        return patronService.findPatronById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Patron> createPatron(@RequestBody Patron patron) {
        Patron newPatron = patronService.savePatron(patron);
        return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Integer id, @RequestBody Patron patron) {
        return new ResponseEntity<>(patronService.updatePatron(id, patron), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Integer id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
