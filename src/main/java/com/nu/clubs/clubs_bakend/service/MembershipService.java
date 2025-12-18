package com.nu.clubs.clubs_bakend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;

@Service
public class MembershipService {
    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    public Optional<Membership> findById(Long id) {
        return membershipRepository.findById(id);
    }

    @Transactional
    public Membership createMembership(Membership membership) {
        membership.setAppliedAt(LocalDateTime.now());
        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership updateMembership(Long id, Membership updatedMembership) {
        Membership existing = membershipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Membership not found with id: " + id));
        existing.setUserId(updatedMembership.getUserId());
        existing.setClubId(updatedMembership.getClubId());
        if (updatedMembership.getStatus() != null) {
            existing.setStatus(updatedMembership.getStatus());
            existing.setProcessedAt(LocalDateTime.now());
        }
        return membershipRepository.save(existing);
    }

    @Transactional
    public void deleteMembership(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Membership not found with id: " + id));
        membershipRepository.delete(membership);
    }
}
