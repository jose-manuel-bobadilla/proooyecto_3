package com.example.demo.controllers;
import com.example.demo.entities.Seat;
import com.example.demo.services.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        Seat seat = seatService.findById(id);
        return ResponseEntity.ok(seat);
    }

    // Endpoint para obtener todos los asientos de un evento específico
    @GetMapping("/event/{eventId}")
    public List<Seat> getSeatsByEventId(@PathVariable Long eventId) {
        return seatService.findByEventId(eventId);
    }

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        Seat savedSeat = seatService.save(seat);
        return new ResponseEntity<>(savedSeat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seatDetails) {
        Seat updatedSeat = seatService.update(id, seatDetails);
        return ResponseEntity.ok(updatedSeat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        seatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}