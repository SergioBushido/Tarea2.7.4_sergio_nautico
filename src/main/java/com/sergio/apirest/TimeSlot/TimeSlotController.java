package com.sergio.apirest.TimeSlot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/timeslots")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotService.getAllTimeSlots();
    }

    @GetMapping("/{date}")
    public List<TimeSlot> getTimeSlotsByDate(@PathVariable String date) {
        return timeSlotService.getTimeSlotsByDate(date);
    }
}
