package com.sergio.apirest.Reservation;

import com.sergio.apirest.TimeSlot.TimeSlot;
import com.sergio.apirest.TimeSlot.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservationAndUpdateSeats(Long timeSlotId, Reservation reservation) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new RuntimeException("TimeSlot no disponible"));

        if (timeSlot.getAvailableSeats() <= 0) {
            throw new RuntimeException("No hay plazas disponibles para esta hora");
        }

        // Reduce el número de asientos disponibles
        timeSlot.setAvailableSeats(timeSlot.getAvailableSeats() - 1);
        timeSlotRepository.save(timeSlot);

        // Establecer el tipo de reserva y asociar el TimeSlot
        reservation.setReservationType("natacion");
        reservation.setTimeSlot(timeSlot);

        // Guarda la reserva
        return reservationRepository.save(reservation);
    }
}
