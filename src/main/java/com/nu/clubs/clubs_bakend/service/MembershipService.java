package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.dto.MembershipRequestDTO;
import com.nu.clubs.clubs_bakend.dto.MembershipResponseDTO;
import com.nu.clubs.clubs_bakend.entity.Club;
import com.nu.clubs.clubs_bakend.entity.Membership;
import com.nu.clubs.clubs_bakend.entity.User;
import com.nu.clubs.clubs_bakend.enums.MembershipRole;
import com.nu.clubs.clubs_bakend.enums.MembershipStatus;
import com.nu.clubs.clubs_bakend.exception.ResourceNotFoundException;
import com.nu.clubs.clubs_bakend.repository.ClubRepository;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;
import com.nu.clubs.clubs_bakend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    @Transactional
    public MembershipResponseDTO createMembership(MembershipRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDTO.getUserId()));

        Club club = clubRepository.findById(requestDTO.getClubId())
                .orElseThrow(() -> new ResourceNotFoundException("Club not found with id: " + requestDTO.getClubId()));

        // Check if membership already exists
        if (membershipRepository.existsByUserAndClub(user, club)) {
            throw new IllegalStateException("Membership already exists for this user and club");
        }

        Membership membership = new Membership();
        membership.setUser(user);
        membership.setClub(club);
        membership.setRole(requestDTO.getRole());
        membership.setStatus(MembershipStatus.PENDING);
        membership.setJoinedAt(LocalDateTime.now());

        Membership savedMembership = membershipRepository.save(membership);
        return convertToDTO(savedMembership);
    }

    @Transactional(readOnly = true)
    public MembershipResponseDTO getMembershipById(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
        return convertToDTO(membership);
    }

    @Transactional(readOnly = true)
    public List<MembershipResponseDTO> getAllMemberships() {
        return membershipRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MembershipResponseDTO> getMembershipsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return membershipRepository.findByUser(user).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MembershipResponseDTO> getMembershipsByClubId(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found with id: " + clubId));
        return membershipRepository.findByClub(club).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MembershipResponseDTO updateMembership(Long id, MembershipRequestDTO requestDTO) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));

        if (requestDTO.getRole() != null) {
            membership.setRole(requestDTO.getRole());
        }
        if (requestDTO.getStatus() != null) {
            membership.setStatus(requestDTO.getStatus());
        }

        Membership updatedMembership = membershipRepository.save(membership);
        return convertToDTO(updatedMembership);
    }

    @Transactional
    public void deleteMembership(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new ResourceNotFoundException("Membership not found with id: " + id);
        }
        membershipRepository.deleteById(id);
    }

    @Transactional
    public MembershipResponseDTO approveMembership(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
        
        membership.setStatus(MembershipStatus.ACTIVE);
        Membership updatedMembership = membershipRepository.save(membership);
        return convertToDTO(updatedMembership);
    }

    @Transactional
    public MembershipResponseDTO rejectMembership(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
        
        membership.setStatus(MembershipStatus.REJECTED);
        Membership updatedMembership = membershipRepository.save(membership);
        return convertToDTO(updatedMembership);
    }

    private MembershipResponseDTO convertToDTO(Membership membership) {
        MembershipResponseDTO dto = new MembershipResponseDTO();
        dto.setId(membership.getId());
        dto.setUserId(membership.getUser().getId());
        dto.setUserName(membership.getUser().getName());
        dto.setClubId(membership.getClub().getId());
        dto.setClubName(membership.getClub().getName());
        dto.setRole(membership.getRole());
        dto.setStatus(membership.getStatus());
        dto.setJoinedAt(membership.getJoinedAt());
        return dto;
    }
}