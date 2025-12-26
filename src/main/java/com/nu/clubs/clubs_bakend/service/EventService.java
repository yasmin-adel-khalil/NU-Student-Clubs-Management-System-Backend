package com.nu.clubs.clubs_bakend.service;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Event;
import com.nu.clubs.clubs_bakend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event getEventOrThrow(Long id) {
        return getEventById(id).orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
    }

    public List<Event> getEventsByClub(Long clubId) {
        return eventRepository.findByClubId(clubId);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event createEvent(Event event) {
        return saveEvent(event);
    }

    public Event updateEvent(Long id, Event event) {
        event.setId(id);
        return saveEvent(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

}
