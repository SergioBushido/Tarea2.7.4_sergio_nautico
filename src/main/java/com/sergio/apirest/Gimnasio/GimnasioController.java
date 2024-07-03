package com.sergio.apirest.Gimnasio;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/gimnasio")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GimnasioController {
    private final GimnasioService gimnasioService;

    @GetMapping
    public List<Gimnasio> getAllTimeSlots() {
        return gimnasioService.getAllTimeSlots();
    }

    @GetMapping("/{date}")
    public List<Gimnasio> getTimeSlotsByDate(@PathVariable String date) {
        return gimnasioService.getTimeSlotsByDate(date);
    }
}
