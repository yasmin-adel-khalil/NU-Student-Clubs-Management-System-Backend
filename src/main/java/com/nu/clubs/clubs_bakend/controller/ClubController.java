package com.nu.clubs.clubs_bakend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.service.ClubService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {
    
    private final ClubService clubService;
    
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club createdClub = clubService.createClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClub);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        return ResponseEntity.ok(club);
    }
    
    @GetMapping
    public ResponseEntity<Page<Club>> getAllClubs(Pageable pageable) {
        Page<Club> clubs = clubService.getAllClubs(pageable);
        return ResponseEntity.ok(clubs);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Club>> searchClubsByName(@RequestParam String name) {
        List<Club> clubs = clubService.searchClubsByName(name);
        return ResponseEntity.ok(clubs);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club) {
        Club updatedClub = clubService.updateClub(id, club);
        return ResponseEntity.ok(updatedClub);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
}
