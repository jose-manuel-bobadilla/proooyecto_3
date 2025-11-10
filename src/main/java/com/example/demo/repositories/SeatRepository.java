package com.example.demo.repositories;

import com.example.demo.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {


    List<Seat> findByEvent_Id(Long eventId);


    List<Seat> findByUser_Id(Long userId);

}
