package com.sergio.apirest.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> findAllPatrones() {
        return patronRepository.findAll();
    }

    public Optional<Patron> findPatronById(Integer id) {
        return patronRepository.findById(id);
    }

    @Transactional
    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    public Patron updatePatron(Integer id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + id));
        patron.setNombre(patronDetails.getNombre());
        patron.setLicencia(patronDetails.getLicencia());
        return patronRepository.save(patron);
    }

    @Transactional
    public void deletePatron(Integer id) {
        patronRepository.deleteById(id);
    }
}
