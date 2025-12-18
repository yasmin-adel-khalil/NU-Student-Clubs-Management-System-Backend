package com.nu.clubs.clubs_bakend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nu.clubs.clubs_bakend.model.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
