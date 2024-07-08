package com.sergio.apirest.Reservation;

import com.sergio.apirest.Gimnasio.Gimnasio;
import com.sergio.apirest.Gimnasio.GimnasioRepository;
import com.sergio.apirest.Natacion.Natacion;
import com.sergio.apirest.Natacion.NatacionRepository;
import com.sergio.apirest.repositories.UserRepository;
import com.sergio.apirest.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final NatacionRepository natacionRepository;
    private final GimnasioRepository gimnasioRepository;
    private final UserRepository userRepository;


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservationAndUpdateSeats(Long natacionId, Reservation reservation) {
        Natacion natacion = natacionRepository.findById(natacionId)
                .orElseThrow(() -> new RuntimeException("Natacion no disponible"));

        if (natacion.getAvailableSeats() <= 0) {
            throw new RuntimeException("No hay plazas disponibles para esta hora");
        }

        // Reduce el número de asientos disponibles
        natacion.setAvailableSeats(natacion.getAvailableSeats() - 1);
        natacionRepository.save(natacion);

        // Establecer el tipo de reserva y asociar la Natacion
        reservation.setReservationType("natacion");
        reservation.setNatacion(natacion);

        // Guarda la reserva
        return reservationRepository.save(reservation);
    }

    public Reservation createReservationGym(Long gimnasioId, Reservation reservation) {
        Gimnasio gimnasio = gimnasioRepository.findById(gimnasioId)
                .orElseThrow(() -> new RuntimeException("Gimnasio no disponible"));

        if (gimnasio.getAvailableSeats() <= 0) {
            throw new RuntimeException("No hay plazas disponibles para esta hora");
        }

        // Reduce el número de asientos disponibles
        gimnasio.setAvailableSeats(gimnasio.getAvailableSeats() - 1);
        gimnasioRepository.save(gimnasio);

        // Establecer el tipo de reserva y asociar el Gimnasio
        reservation.setReservationType("gimnasio");
        reservation.setGimnasio(gimnasio);

        // Guarda la reserva
        return reservationRepository.save(reservation);
    }
/*// crear y asignar reservas a usuarios
    public Reservation createReservation(Reservation reservation, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        reservation.setUser(user);
        return reservationRepository.save(reservation);
    }*/


}

/*
import com.sergio.apirest.Gimnasio.Gimnasio;
import com.sergio.apirest.Gimnasio.GimnasioRepository;
import com.sergio.apirest.Natacion.Natacion;
import com.sergio.apirest.Natacion.NatacionRepository;
import com.sergio.apirest.repositories.UserRepository;
import com.sergio.apirest.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final NatacionRepository natacionRepository;
    private final GimnasioRepository gimnasioRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository,
                              NatacionRepository natacionRepository, GimnasioRepository gimnasioRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.natacionRepository = natacionRepository;
        this.gimnasioRepository = gimnasioRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservationAndUpdateSeats(Long natacionId, Long userId, Reservation reservation) {
        Natacion natacion = natacionRepository.findById(natacionId).orElseThrow(() -> new RuntimeException("Natacion not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (natacion.getAvailableSeats() <= 0) {
            throw new RuntimeException("No available seats for this natacion class");
        }

        natacion.setAvailableSeats(natacion.getAvailableSeats() - 1);
        reservation.setNatacion(natacion);
        reservation.setUser(user);

        return reservationRepository.save(reservation);
    }

    public Reservation createReservationGym(Long gimnasioId, Long userId, Reservation reservation) {
        Gimnasio gimnasio = gimnasioRepository.findById(gimnasioId).orElseThrow(() -> new RuntimeException("Gimnasio not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (gimnasio.getAvailableSeats() <= 0) {
            throw new RuntimeException("No available seats for this gym session");
        }

        gimnasio.setAvailableSeats(gimnasio.getAvailableSeats() - 1);
        reservation.setGimnasio(gimnasio);
        reservation.setUser(user);

        return reservationRepository.save(reservation);
    }
}*/