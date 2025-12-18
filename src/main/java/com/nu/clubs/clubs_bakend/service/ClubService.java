package com.nu.clubs.clubs_bakend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.repository.ClubRepository;

@Service
public class ClubService {
	private final ClubRepository clubRepository;

	public ClubService(ClubRepository clubRepository) {
		this.clubRepository = clubRepository;
	}

	public List<Club> findAll() {
		return clubRepository.findAll();
	}

	public Optional<Club> findById(Long id) {
		return clubRepository.findById(id);
	}
}
