package com.sergio.apirest.tenis;

import com.sergio.apirest.Reservation.Reservation;
import com.sergio.apirest.Reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenisService {
    private final ReservationRepository reservationRepository;
    private final TenisRepository tenisRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Tenis> getAllTimeSlots() {
        return tenisRepository.findAll();
    }

    public List<Tenis> getTimeSlotsByDate(String date) {
        return tenisRepository.findByDate(date);
    }
    public boolean createReservation(Long tenisId, Reservation reservation) {
        Optional<Tenis> tenisSlot = tenisRepository.findById(tenisId);
        if (tenisSlot.isPresent()) {
            Tenis tenis = tenisSlot.get();
            if (tenis.getAvailableSeats() > 0) {
                tenis.getReservations().add(reservation);
                reservation.setTenis(tenis);
                reservationRepository.save(reservation);
                tenis.setAvailableSeats(tenis.getAvailableSeats() - 1);
                tenisRepository.save(tenis);
                return true;
            }
        }
        return false;
    }
}
