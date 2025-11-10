package com.example.demo.services.Impl;


import com.example.demo.entities.Event;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.EventRepository;
import com.example.demo.services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Evento no encontrado con ID: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Override
    public Event update(Long id, Event eventDetails) {
        Event event = findById(id);

        event.setNombre(eventDetails.getNombre());
        event.setFecha(eventDetails.getFecha());
        event.setLugar(eventDetails.getLugar());
        event.setPrecioBase(eventDetails.getPrecioBase());
        event.setEstado(eventDetails.getEstado());

        return eventRepository.save(event);
    }
}