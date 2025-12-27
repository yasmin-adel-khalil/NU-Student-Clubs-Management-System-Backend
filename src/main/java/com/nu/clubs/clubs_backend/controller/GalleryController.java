package com.nu.clubs.clubs_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.model.Gallery;
import com.nu.clubs.clubs_backend.service.GalleryService;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "http://localhost:4200")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @GetMapping
    public ResponseEntity<List<Gallery>> getAllGallery() {
        List<Gallery> galleries = galleryService.getAllGallery();
        return ResponseEntity.ok(galleries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable Long id) {
        Gallery gallery = galleryService.getGalleryById(id);
        return ResponseEntity.ok(gallery);
    }

    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery gallery) {
        Gallery created = galleryService.createGallery(gallery);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gallery> updateGallery(@PathVariable Long id, @RequestBody Gallery gallery) {
        Gallery updated = galleryService.updateGallery(id, gallery);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
        return ResponseEntity.noContent().build();
    }
}
