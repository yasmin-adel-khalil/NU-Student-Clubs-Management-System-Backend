package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Event;
import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByClub(Club club);

    List<Event> findByEventDateBetween(LocalDateTime start, LocalDateTime end);
}
