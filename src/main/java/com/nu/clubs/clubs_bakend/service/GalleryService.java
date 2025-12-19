package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Gallery;
import com.nu.clubs.clubs_bakend.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    public Gallery createGallery(Gallery g) {
        return galleryRepository.save(g);
    }

    public Gallery updateGallery(Long id, Gallery details) {
        Gallery g = galleryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gallery item not found: " + id));
        if (details.getTitle() != null)
            g.setTitle(details.getTitle());
        if (details.getDescription() != null)
            g.setDescription(details.getDescription());
        if (details.getImageUrl() != null)
            g.setImageUrl(details.getImageUrl());
        if (details.getClub() != null)
            g.setClub(details.getClub());
        return galleryRepository.save(g);
    }

    public Gallery getGalleryById(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gallery item not found: " + id));
    }

    public List<Gallery> getAllGallery() {
        return galleryRepository.findAll();
    }

    public void deleteGallery(Long id) {
        if (!galleryRepository.existsById(id))
            throw new NotFoundException("Gallery item not found: " + id);
        galleryRepository.deleteById(id);
    }
}
