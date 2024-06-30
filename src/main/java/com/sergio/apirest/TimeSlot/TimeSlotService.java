package com.sergio.apirest.TimeSlot;

import com.sergio.apirest.Reservation.Reservation;
import com.sergio.apirest.Reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotService {
    private final ReservationRepository reservationRepository;
    private final TimeSlotRepository timeSlotRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public List<TimeSlot> getTimeSlotsByDate(String date) {
        return timeSlotRepository.findByDate(date);
    }
}
