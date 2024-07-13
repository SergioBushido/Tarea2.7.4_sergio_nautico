package com.sergio.apirest.tenis;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/tenis")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TenisController {
    private final TenisService tenisService;

    @GetMapping
    public List<Tenis> getAllTimeSlots() {
        return tenisService.getAllTimeSlots();
    }

    @GetMapping("/{date}")
    public List<Tenis> getTimeSlotsByDate(@PathVariable String date) {
        return tenisService.getTimeSlotsByDate(date);
    }
  }
