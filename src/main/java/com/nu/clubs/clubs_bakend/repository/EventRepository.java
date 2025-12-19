package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByClubId(Long clubId);

}
