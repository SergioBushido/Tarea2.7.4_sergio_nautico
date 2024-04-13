package com.sergio.apirest.patron;

import com.sergio.apirest.patron.dto.PatronRequest;
import com.sergio.apirest.patron.dto.PatronRequestMapper;
import com.sergio.apirest.patron.dto.PatronResponse;
import com.sergio.apirest.patron.dto.PatronResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    private final PatronRequestMapper patronRequestMapper;
    private final PatronResponseMapper patronResponseMapper;

    private final PatronRepository patronRepository;
    private final PatronRequest patronRequest;
    private final PatronResponse patronResponse;

    @Autowired
    public PatronService(PatronRequestMapper patronRequestMapper, PatronResponseMapper patronResponseMapper, PatronRepository patronRepository, PatronRequest patronRequest, PatronResponse patronResponse) {
        this.patronRequestMapper = patronRequestMapper;
        this.patronResponseMapper = patronResponseMapper;
        this.patronRepository = patronRepository;
        this.patronRequest = patronRequest;
        this.patronResponse = patronResponse;
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
    public boolean deletePatron(Integer id) {
        if (patronRepository.existsById(id)) {
            patronRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}