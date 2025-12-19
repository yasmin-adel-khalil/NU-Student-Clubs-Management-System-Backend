package com.nu.clubs.clubs_bakend.controller;

import com.nu.clubs.clubs_bakend.dto.EventRequest;
import com.nu.clubs.clubs_bakend.dto.EventResponse;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Event;
import com.nu.clubs.clubs_bakend.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    // Constructor Injection
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // ➡️ Create Event (POST)
    @PostMapping
    public EventResponse createEvent(@RequestBody EventRequest request) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setClubId(request.getClubId());

        Event saved = eventRepository.save(event);

        EventResponse response = new EventResponse();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setEventDate(saved.getEventDate());

        return response;
    }

    // ➡️ Get Events by Club (GET)
    @GetMapping("/club/{clubId}")
    public List<EventResponse> getEventsByClub(@PathVariable Long clubId) {
        return eventRepository.findByClubId(clubId)
                .stream()
                .map(event -> {
                    EventResponse response = new EventResponse();
                    response.setId(event.getId());
                    response.setTitle(event.getTitle());
                    response.setDescription(event.getDescription());
                    response.setEventDate(event.getEventDate());
                    return response;
                })
                .collect(Collectors.toList());
    }

    // ➡️ Delete Event (DELETE)
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        eventRepository.delete(event);
    }
}