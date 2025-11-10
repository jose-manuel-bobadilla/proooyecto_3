package com.example.demo.services;

import com.example.demo.entities.Event;
import java.util.List;

public interface EventService {
    List<Event> findAll();
    Event findById(Long id);
    Event save(Event event);
    void deleteById(Long id);
    Event update(Long id, Event eventDetails);
}
