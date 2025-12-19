package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Committee;
import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Long> {
    List<Committee> findByClub(Club club);
}
