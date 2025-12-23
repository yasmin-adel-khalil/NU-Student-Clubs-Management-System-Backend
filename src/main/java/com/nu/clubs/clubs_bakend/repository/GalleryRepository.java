package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> findByClubId(Long clubId);
}
