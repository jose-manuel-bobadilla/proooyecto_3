package com.example.demo.services;

import com.example.demo.entities.Seat;
import java.util.List;

public interface SeatService {
    List<Seat> findAll();
    Seat findById(Long id);
    List<Seat> findByEventId(Long eventId); // Adicional
    Seat save(Seat seat);
    void deleteById(Long id);
    Seat update(Long id, Seat seatDetails);
}