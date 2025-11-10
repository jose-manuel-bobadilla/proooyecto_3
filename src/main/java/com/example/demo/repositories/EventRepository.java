package com.example.demo.repositories;

import com.example.demo.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Busca todos los eventos que contengan un texto en su nombre, ignorando mayúsculas/minúsculas.
     * Ejemplo: buscar "concierto" encontrará "Concierto de Rock".
     */
    List<Event> findByNombreContainingIgnoreCase(String nombre);

}