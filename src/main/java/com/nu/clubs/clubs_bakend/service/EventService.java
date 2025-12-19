package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Event;
import com.nu.clubs.clubs_bakend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event e) {
        return eventRepository.save(e);
    }

    public Event updateEvent(Long id, Event details) {
        Event e = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found: " + id));
        if (details.getTitle() != null)
            e.setTitle(details.getTitle());
        if (details.getDescription() != null)
            e.setDescription(details.getDescription());
        if (details.getEventDate() != null)
            e.setEventDate(details.getEventDate());
        if (details.getLocation() != null)
            e.setLocation(details.getLocation());
        if (details.getClub() != null)
            e.setClub(details.getClub());
        return eventRepository.save(e);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found: " + id));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id))
            throw new NotFoundException("Event not found: " + id);
        eventRepository.deleteById(id);
    }
}
