package com.sergio.apirest.Gimnasio;

import com.sergio.apirest.Natacion.Natacion;
import com.sergio.apirest.Natacion.NatacionRepository;
import com.sergio.apirest.Reservation.Reservation;
import com.sergio.apirest.Reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GimnasioService {
    private final ReservationRepository reservationRepository;
    private final GimnasioRepository gimnasioRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Gimnasio> getAllTimeSlots() {
        return gimnasioRepository.findAll();
    }

    public List<Gimnasio> getTimeSlotsByDate(String date) {
        return gimnasioRepository.findByDate(date);
    }
}
