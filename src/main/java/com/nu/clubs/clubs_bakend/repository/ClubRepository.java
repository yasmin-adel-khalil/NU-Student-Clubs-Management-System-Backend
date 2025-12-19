package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
}
