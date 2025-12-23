package com.nu.clubs.clubs_bakend.service;
import com.nu.clubs.clubs_bakend.dto.GalleryRequest;
import com.nu.clubs.clubs_bakend.dto.GalleryResponse;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Gallery;
import com.nu.clubs.clubs_bakend.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public List<Gallery> getAll() {
        return galleryRepository.findAll();
    }

    public Optional<Gallery> getById(Long id) {
        return galleryRepository.findById(id);
    }

    public List<Gallery> getByClub(Long clubId) {
        return galleryRepository.findByClubId(clubId);
    }

    public Gallery save(Gallery gallery) {
        return galleryRepository.save(gallery);
    }
    public void delete(Long id) {
        galleryRepository.deleteById(id);
    }
}

