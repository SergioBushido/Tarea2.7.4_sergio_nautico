package com.sergio.apirest.patron;

import com.sergio.apirest.barco.Barco;
import com.sergio.apirest.patron.dto.PatronRequest;
import com.sergio.apirest.patron.dto.PatronRequestMapper;
import com.sergio.apirest.patron.dto.PatronResponse;
import com.sergio.apirest.patron.dto.PatronResponseMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatronService {

    private final PatronRequestMapper patronRequestMapper;
    private final PatronResponseMapper patronResponseMapper;
    private final PatronRepository patronRepository;



    public List<PatronResponse> findAllPatrones() {
        return patronResponseMapper.entitiesToResponses(patronRepository.findAll());
    }

    public Optional<PatronResponse> findPatronById(final Integer id) {
        final Optional<Patron> patronOptional = patronRepository.findById(id);
        return patronOptional.map(patronResponseMapper::entityToResponse);
    }

    @Transactional
    public PatronResponse savePatron(final PatronRequest patronRequest) {
        Patron patron = patronRequestMapper.dtoToEntity(patronRequest);
        patron = patronRepository.save(patron);
        return patronResponseMapper.entityToResponse(patron);
    }

    @Transactional
    public Optional<PatronResponse> updatePatron(final Integer id, final PatronRequest patronDetails) {
        final Optional<Patron> existingPatron = patronRepository.findById(id);
        existingPatron.ifPresent(patron -> {
            patronResponseMapper.updateEntityFromDto(patronDetails, patron);
            patronRepository.save(patron);
        });
        return existingPatron.map(patronResponseMapper::entityToResponse);
    }

    @Transactional
    public boolean deletePatron(final Integer id) {
        if (patronRepository.existsById(id)) {
            patronRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}