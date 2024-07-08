package com.sergio.apirest.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/natacion/{natacionId}")
    public ResponseEntity<Reservation> createReservation(@PathVariable Long natacionId, @RequestBody Reservation reservation) {
        try {
            Reservation createdReservation = reservationService.createReservationAndUpdateSeats(natacionId, reservation);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/gimnasio/{gimnasioId}")
    public ResponseEntity<Reservation> createReservationGym(@PathVariable Long gimnasioId, @RequestBody Reservation reservation) {
        try {
            Reservation createdReservation = reservationService.createReservationGym(gimnasioId, reservation);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    /*
@PostMapping("/natacion/{natacionId}/user/{userId}")
public ResponseEntity<Reservation> createReservation(@PathVariable Long natacionId, @PathVariable Long userId, @RequestBody Reservation reservation) {
    try {
        Reservation createdReservation = reservationService.createReservationAndUpdateSeats(natacionId, userId, reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}

    @PostMapping("/gimnasio/{gimnasioId}/user/{userId}")
    public ResponseEntity<Reservation> createReservationGym(@PathVariable Long gimnasioId, @PathVariable Long userId, @RequestBody Reservation reservation) {
        try {
            Reservation createdReservation = reservationService.createReservationGym(gimnasioId, userId, reservation);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}*/