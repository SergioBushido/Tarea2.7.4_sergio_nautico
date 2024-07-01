package com.sergio.apirest.Natacion;

import com.sergio.apirest.Reservation.Reservation;
import com.sergio.apirest.Reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NatacionService {
    private final ReservationRepository reservationRepository;
    private final NatacionRepository natacionRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Natacion> getAllTimeSlots() {
        return natacionRepository.findAll();
    }

    public List<Natacion> getTimeSlotsByDate(String date) {
        return natacionRepository.findByDate(date);
    }
}
