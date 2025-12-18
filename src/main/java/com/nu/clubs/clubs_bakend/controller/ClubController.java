package com.nu.clubs.clubs_bakend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nu.clubs.clubs_bakend.dto.ClubResponse;
import com.nu.clubs.clubs_bakend.dto.mapper.ClubMapper;
import com.nu.clubs.clubs_bakend.service.ClubService;

@RestController
@RequestMapping("/clubs")
public class ClubController {
	private final ClubService clubService;

	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}

	@GetMapping
	public List<ClubResponse> getAllClubs() {
		return clubService.findAll()
				.stream()
				.map(ClubMapper::toResponse)
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClubResponse> getClubById(@PathVariable Long id) {
		return clubService.findById(id)
				.map(ClubMapper::toResponse)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
