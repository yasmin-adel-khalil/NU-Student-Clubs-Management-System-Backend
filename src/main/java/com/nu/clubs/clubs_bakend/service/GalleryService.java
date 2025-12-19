package com.nu.clubs.clubs_bakend.service;
import com.nu.clubs.clubs_bakend.dto.GalleryRequest;
import com.nu.clubs.clubs_bakend.dto.GalleryResponse;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Gallery;
import com.nu.clubs.clubs_bakend.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public GalleryResponse addMedia(GalleryRequest request) {
        Gallery gallery = new Gallery();
        gallery.setMediaUrl(request.getMediaUrl());
        gallery.setMediaType(request.getMediaType());
        gallery.setClubId(request.getClubId());

        Gallery saved = galleryRepository.save(gallery);

        GalleryResponse response = new GalleryResponse();
        response.setId(saved.getId());
        response.setMediaUrl(saved.getMediaUrl());
        response.setMediaType(saved.getMediaType());

        return response;
    }

    public List<GalleryResponse> getMediaByClub(Long clubId) {
        return galleryRepository.findByClubId(clubId)
                .stream()
                .map(media -> {
                    GalleryResponse response = new GalleryResponse();
                    response.setId(media.getId());
                    response.setMediaUrl(media.getMediaUrl());
                    response.setMediaType(media.getMediaType());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public void deleteMedia(Long id) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow();

        galleryRepository.delete(gallery);
    }
}
