package com.nu.clubs.clubs_bakend.controller;

import com.nu.clubs.clubs_bakend.dto.GalleryRequest;
import com.nu.clubs.clubs_bakend.dto.GalleryResponse;
import com.nu.clubs.clubs_bakend.service.GalleryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping
    public GalleryResponse addMedia(@RequestBody GalleryRequest request) {
        return galleryService.addMedia(request);
    }

    @GetMapping("/club/{clubId}")
    public List<GalleryResponse> getMediaByClub(@PathVariable Long clubId) {
        return galleryService.getMediaByClub(clubId);
    }

    @DeleteMapping("/{id}")
    public void deleteMedia(@PathVariable Long id) {
        galleryService.deleteMedia(id);
    }
}
