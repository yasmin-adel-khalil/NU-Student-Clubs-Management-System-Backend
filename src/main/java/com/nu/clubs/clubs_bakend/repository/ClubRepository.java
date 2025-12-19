package com.nu.clubs.clubs_bakend.repository;

import java.util.List;
import java.util.Optional;

import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByName(String name);

    List<Club> findByNameContainingIgnoreCase(String name);

    List<Club> findByCategory(String category);
}
