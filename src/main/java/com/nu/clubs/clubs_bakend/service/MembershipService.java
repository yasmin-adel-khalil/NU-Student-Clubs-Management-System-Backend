package com.nu.clubs.clubs_bakend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nu.clubs.clubs_bakend.exception.BadRequestException;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final MembershipRepository membershipRepository;

    @Transactional
    public Membership createMembership(Membership membership) {
        boolean exists = membershipRepository.findAll().stream()
            .anyMatch(m -> m.getUserId().equals(membership.getUserId()) 
                        && m.getClubId().equals(membership.getClubId()));
        
        if (exists) {
            throw new BadRequestException("User is already a member of this club");
        }
        
        return membershipRepository.save(membership);
    }

    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    public Membership findById(Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found with id: " + id));
    }

    @Transactional
    public void delete(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new NotFoundException("Membership not found with id: " + id);
        }
        membershipRepository.deleteById(id);
    }
}
