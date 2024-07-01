package com.sergio.apirest.Reservation;

import com.sergio.apirest.Natacion.Natacion;
import com.sergio.apirest.Natacion.NatacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final NatacionRepository natacionRepository;

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

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
}
