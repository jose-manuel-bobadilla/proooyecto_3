package com.example.demo.repositories;

import com.example.demo.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    /**
     * Busca todos los asientos que pertenecen a un evento específico,
     * usando el ID del evento. Es fundamental para poder listar los asientos
     * de un evento en particular.
     */
    List<Seat> findByEvent_Id(Long eventId);

    /**
     * Busca todos los asientos reservados por un usuario específico,
     * usando el ID del usuario.
     */
    List<Seat> findByUser_Id(Long userId);

}
