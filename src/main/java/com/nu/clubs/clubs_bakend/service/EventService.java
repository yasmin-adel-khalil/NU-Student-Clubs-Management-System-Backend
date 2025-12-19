package com.nu.clubs.clubs_bakend.service;
import com.nu.clubs.clubs_bakend.dto.EventRequest;
import com.nu.clubs.clubs_bakend.dto.EventResponse;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Event;
import com.nu.clubs.clubs_bakend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponse createEvent(EventRequest request) {
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

    public List<EventResponse> getEventsByClub(Long clubId) {
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

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow();

        eventRepository.delete(event);
    }
}
