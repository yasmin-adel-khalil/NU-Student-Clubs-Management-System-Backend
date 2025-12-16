package com.nu.clubs.clubs_bakend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Column
    private String location;

    @Column(name = "event_date")
    private Long eventDate;

    @Column(name = "end_date")
    private Long endDate;

    @Column
    private String category;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column
    private Integer capacity;

    @Column(name = "attendees_count")
    private Integer attendeesCount = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt = System.currentTimeMillis();

    @Column(name = "updated_at")
    private Long updatedAt = System.currentTimeMillis();

    public Event() {}

    public Event(String title, Club club) {
        this.title = title;
        this.club = club;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Long getEventDate() { return eventDate; }
    public void setEventDate(Long eventDate) { this.eventDate = eventDate; }

    public Long getEndDate() { return endDate; }
    public void setEndDate(Long endDate) { this.endDate = endDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Integer getAttendeesCount() { return attendeesCount; }
    public void setAttendeesCount(Integer attendeesCount) { this.attendeesCount = attendeesCount; }

    public Long getCreatedAt() { return createdAt; }
    public void setCreatedAt(Long createdAt) { this.createdAt = createdAt; }

    public Long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Long updatedAt) { this.updatedAt = updatedAt; }
}
