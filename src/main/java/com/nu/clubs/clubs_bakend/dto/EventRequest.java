package com.nu.clubs.clubs_bakend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventRequest {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private Long clubId;
    public EventRequest() {}

    public EventRequest(String title, String description, LocalDateTime eventDate, Long clubId) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.clubId = clubId;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public Long getClubId() { return clubId; }
    public void setClubId(Long clubId) { this.clubId = clubId; }
}

