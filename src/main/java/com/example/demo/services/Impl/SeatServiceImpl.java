package com.example.demo.services.Impl;

import com.example.demo.entities.Seat;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.SeatRepository;
import com.example.demo.services.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asiento no encontrado con ID: " + id));
    }

    @Override
    public List<Seat> findByEventId(Long eventId) {
        return seatRepository.findByEvent_Id(eventId);
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void deleteById(Long id) {
        if (!seatRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Asiento no encontrado con ID: " + id);
        }
        seatRepository.deleteById(id);
    }

    @Override
    public Seat update(Long id, Seat seatDetails) {
        Seat seat = findById(id);

        seat.setEstado(seatDetails.getEstado());
        // Lógica para asignar un asiento a un usuario
        if (seatDetails.getUser() != null) {
            seat.setUser(seatDetails.getUser());
        }

        return seatRepository.save(seat);
    }
}