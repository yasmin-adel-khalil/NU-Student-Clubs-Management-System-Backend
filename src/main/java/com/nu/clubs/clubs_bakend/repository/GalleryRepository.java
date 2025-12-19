package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Gallery;
import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> findByClub(Club club);
}
