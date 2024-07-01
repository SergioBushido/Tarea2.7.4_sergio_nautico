package com.sergio.apirest.Natacion;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/natacion")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class NatacionController {
    private final NatacionService natacionService;

    @GetMapping
    public List<Natacion> getAllTimeSlots() {
        return natacionService.getAllTimeSlots();
    }

    @GetMapping("/{date}")
    public List<Natacion> getTimeSlotsByDate(@PathVariable String date) {
        return natacionService.getTimeSlotsByDate(date);
    }
}
