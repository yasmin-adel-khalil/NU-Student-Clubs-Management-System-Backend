package com.nu.clubs.clubs_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Committee;

import java.util.List;

@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Long> {
    List<Committee> findByClub(Club club);
}
