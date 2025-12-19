package com.nu.clubs.clubs_bakend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nu.clubs.clubs_bakend.dto.ClubRequest;
import com.nu.clubs.clubs_bakend.dto.ClubResponse;
import com.nu.clubs.clubs_bakend.dto.mapper.ClubMapper;
import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.service.ClubService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
@Validated
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<ClubResponse> createClub(@Valid @RequestBody ClubRequest clubRequest) {
        Club club = ClubMapper.toEntity(clubRequest);
        Club createdClub = clubService.createClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClubMapper.toResponse(createdClub));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubResponse> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        return ResponseEntity.ok(ClubMapper.toResponse(club));
    }

    @GetMapping
    public ResponseEntity<Page<ClubResponse>> getAllClubs(Pageable pageable) {
        Page<Club> clubs = clubService.getAllClubs(pageable);
        Page<ClubResponse> responses = clubs.map(ClubMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClubResponse>> searchClubsByName(@RequestParam String name) {
        List<Club> clubs = clubService.searchClubsByName(name);
        List<ClubResponse> responses = clubs.stream()
                .map(ClubMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ClubResponse>> getClubsByCategory(@RequestParam String category) {
        List<Club> clubs = clubService.findByCategory(category);
        List<ClubResponse> responses = clubs.stream()
                .map(ClubMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubResponse> updateClub(@PathVariable Long id, @Valid @RequestBody ClubRequest clubRequest) {
        Club existingClub = clubService.getClubById(id);
        ClubMapper.updateEntity(clubRequest, existingClub);
        Club updatedClub = clubService.updateClub(id, existingClub);
        return ResponseEntity.ok(ClubMapper.toResponse(updatedClub));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
}
