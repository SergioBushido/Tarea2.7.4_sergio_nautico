package com.sergio.apirest.TimeSlot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/timeslots")
@CrossOrigin(origins = "http://localhost:4200")
public class TimeSlotController {
    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotService.getAllTimeSlots();
    }

    @GetMapping("/{date}")
    public List<TimeSlot> getTimeSlotsByDate(@PathVariable String date) {
        return timeSlotService.getTimeSlotsByDate(date);
    }
}
