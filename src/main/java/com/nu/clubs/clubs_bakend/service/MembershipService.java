package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.MembershipStatus;
import com.nu.clubs.clubs_bakend.model.User;
import com.nu.clubs.clubs_bakend.repository.ClubRepository;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;
import com.nu.clubs.clubs_bakend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    public Membership findById(Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found with id: " + id));
    }

    public List<Membership> findByStatus(MembershipStatus status) {
        return membershipRepository.findByStatus(status);
    }

    public List<Membership> findByClub(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NotFoundException("Club not found with id: " + clubId));
        return membershipRepository.findByClub(club);
    }

    public List<Membership> findByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        return membershipRepository.findByUser(user);
    }

    public Membership create(Long clubId, Long userId, String role) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NotFoundException("Club not found with id: " + clubId));
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Membership membership = new Membership();
        membership.setClub(club);
        membership.setUser(user);
        membership.setRole(role);
        membership.setStatus(MembershipStatus.PENDING);
        membership.setJoinedAt(LocalDateTime.now());

        return membershipRepository.save(membership);
    }

    public Membership updateStatus(Long id, MembershipStatus status) {
        Membership membership = findById(id);
        membership.setStatus(status);
        return membershipRepository.save(membership);
    }

    public void delete(Long id) {
        Membership membership = findById(id);
        membershipRepository.delete(membership);
    }
}
