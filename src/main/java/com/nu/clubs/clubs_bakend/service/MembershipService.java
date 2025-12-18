package com.nu.clubs.clubs_bakend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nu.clubs.clubs_bakend.exception.BadRequestException;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.MembershipStatus;
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
    public void delete(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new NotFoundException("Membership not found with id: " + id);
        }
        membershipRepository.deleteById(id);
    }

    @Transactional
    public Membership approveMembership(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found with id: " + id));
        
        if (membership.getStatus() != MembershipStatus.PENDING) {
            throw new BadRequestException("Only PENDING memberships can be approved");
        }
        
        membership.setStatus(MembershipStatus.APPROVED);
        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership rejectMembership(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found with id: " + id));
        
        if (membership.getStatus() != MembershipStatus.PENDING) {
            throw new BadRequestException("Only PENDING memberships can be rejected");
        }
        
        membership.setStatus(MembershipStatus.REJECTED);
        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership withdrawMembership(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found with id: " + id));
        
        if (membership.getStatus() != MembershipStatus.APPROVED && membership.getStatus() != MembershipStatus.ACTIVE) {
            throw new BadRequestException("Only APPROVED or ACTIVE memberships can be withdrawn");
        }
        
        membership.setStatus(MembershipStatus.INACTIVE);
        return membershipRepository.save(membership);
    }}