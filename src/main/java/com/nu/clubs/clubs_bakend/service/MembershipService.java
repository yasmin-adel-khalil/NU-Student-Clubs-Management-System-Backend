package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.dto.MembershipDto;
import com.nu.clubs.clubs_bakend.entity.Membership;
import com.nu.clubs.clubs_bakend.entity.Club;
import com.nu.clubs.clubs_bakend.entity.User;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;
import com.nu.clubs.clubs_bakend.repository.ClubRepository;
import com.nu.clubs.clubs_bakend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MembershipService {

    private MembershipRepository membershipRepository;
    private ClubRepository clubRepository;
    private UserRepository userRepository;

    public MembershipDto createMembership(MembershipDto membershipDto) {
        Membership membership = mapToEntity(membershipDto);
        Membership savedMembership = membershipRepository.save(membership);
        return mapToDto(savedMembership);
    }

    public MembershipDto getMembershipById(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));
        return mapToDto(membership);
    }

    public List<MembershipDto> getAllMemberships() {
        List<Membership> memberships = membershipRepository.findAll();
        return memberships.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MembershipDto updateMembership(Long id, MembershipDto membershipDto) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));

        membership.setRole(membershipDto.getRole());
        membership.setJoinDate(membershipDto.getJoinDate());

        Membership updatedMembership = membershipRepository.save(membership);
        return mapToDto(updatedMembership);
    }

    public void deleteMembership(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));
        membershipRepository.delete(membership);
    }

    private MembershipDto mapToDto(Membership membership) {
        MembershipDto dto = new MembershipDto();
        dto.setId(membership.getId());
        dto.setClubId(membership.getClub().getId());
        dto.setUserId(membership.getUser().getId());
        dto.setRole(membership.getRole());
        dto.setJoinDate(membership.getJoinDate());
        return dto;
    }

    private Membership mapToEntity(MembershipDto dto) {
        Membership membership = new Membership();
        
        Club club = clubRepository.findById(dto.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + dto.getClubId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        membership.setClub(club);
        membership.setUser(user);
        membership.setRole(dto.getRole());
        membership.setJoinDate(dto.getJoinDate());
        
        return membership;
    }
}