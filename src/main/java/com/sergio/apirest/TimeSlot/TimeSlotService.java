package com.sergio.apirest.TimeSlot;

import com.sergio.apirest.Reservation.Reservation;
import com.sergio.apirest.Reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TimeSlotService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation) {
        String formattedDate = dateFormat.format(reservation.getDate());
        TimeSlot timeSlot = timeSlotRepository.findByDateAndHour(formattedDate, reservation.getHour())
                .orElseThrow(() -> new RuntimeException("Hora no disponible"));

        if (timeSlot.getAvailableSeats() <= 0) {
            throw new RuntimeException("No hay plazas disponibles para esta hora");
        }

        timeSlot.setAvailableSeats(timeSlot.getAvailableSeats() - 1);
        timeSlotRepository.save(timeSlot);

        return reservationRepository.save(reservation);
    }

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public List<TimeSlot> getTimeSlotsByDate(String date) {
        return timeSlotRepository.findByDate(date);
    }
}
