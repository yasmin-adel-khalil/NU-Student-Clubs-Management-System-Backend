package com.nu.clubs.clubs_bakend.service;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Gallery;
import com.nu.clubs.clubs_bakend.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Gallery getGalleryById(Long id) {
        return getById(id).orElseThrow(() -> new NotFoundException("Gallery not found with id: " + id));
    }

    public List<Gallery> getByClub(Long clubId) {
        return galleryRepository.findByClubId(clubId);
    }

    public Gallery save(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    public Gallery createGallery(Gallery gallery) {
        return save(gallery);
    }

    public Gallery updateGallery(Long id, Gallery gallery) {
        gallery.setId(id);
        return save(gallery);
    }
    public void delete(Long id) {
        galleryRepository.deleteById(id);
    }
}

