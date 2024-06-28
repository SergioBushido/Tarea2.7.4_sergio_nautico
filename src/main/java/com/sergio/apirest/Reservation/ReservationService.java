package com.sergio.apirest.Reservation;

import com.sergio.apirest.TimeSlot.TimeSlot;
import com.sergio.apirest.TimeSlot.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservationAndUpdateSeats(Reservation reservation) {
        String formattedDate = dateFormat.format(reservation.getDate());
        TimeSlot timeSlot = timeSlotRepository.findByDateAndHour(formattedDate, reservation.getHour())
                .orElseThrow(() -> new RuntimeException("Hora no disponible"));

        if (timeSlot.getAvailableSeats() <= 0) {
            throw new RuntimeException("No hay plazas disponibles para esta hora");
        }

        // Reduce el número de asientos disponibles
        timeSlot.setAvailableSeats(timeSlot.getAvailableSeats() - 1);
        timeSlotRepository.save(timeSlot);

        // Guarda la reserva
        return reservationRepository.save(reservation);
    }
}
