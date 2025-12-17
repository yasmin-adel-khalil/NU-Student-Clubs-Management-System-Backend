package com.nu.clubs.clubs_bakend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nu.clubs.clubs_bakend.exception.BadRequestException;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.Membership.MembershipStatus;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipService {
    
    private final MembershipRepository membershipRepository;
    
    @Transactional
    public Membership applyToClub(Long userId, Long clubId) {
        // Check if already applied
        membershipRepository.findByUserIdAndClubId(userId, clubId)
            .ifPresent(m -> {
                throw new BadRequestException("User already has an application for this club");
            });
        
        Membership membership = new Membership();
        membership.setUserId(userId);
        membership.setClubId(clubId);
        membership.setStatus(MembershipStatus.PENDING);
        
        return membershipRepository.save(membership);
    }
    
    @Transactional
    public Membership approveMembership(Long membershipId) {
        Membership membership = getMembershipById(membershipId);
        
        if (membership.getStatus() != MembershipStatus.PENDING) {
            throw new BadRequestException("Only pending memberships can be approved");
        }
        
        membership.setStatus(MembershipStatus.APPROVED);
        membership.setProcessedAt(LocalDateTime.now());
        
        return membershipRepository.save(membership);
    }
    
    @Transactional
    public Membership rejectMembership(Long membershipId) {
        Membership membership = getMembershipById(membershipId);
        
        if (membership.getStatus() != MembershipStatus.PENDING) {
            throw new BadRequestException("Only pending memberships can be rejected");
        }
        
        membership.setStatus(MembershipStatus.REJECTED);
        membership.setProcessedAt(LocalDateTime.now());
        
        return membershipRepository.save(membership);
    }
    
    @Transactional
    public void withdrawMembership(Long membershipId) {
        Membership membership = getMembershipById(membershipId);
        
        if (membership.getStatus() != MembershipStatus.PENDING) {
            throw new BadRequestException("Only pending memberships can be withdrawn");
        }
        
        membership.setStatus(MembershipStatus.WITHDRAWN);
        membershipRepository.save(membership);
    }
    
    public Membership getMembershipById(Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found with id: " + id));
    }
    
    public List<Membership> getMembershipsByUser(Long userId) {
        return membershipRepository.findByUserId(userId);
    }
    
    public List<Membership> getMembershipsByClub(Long clubId) {
        return membershipRepository.findByClubId(clubId);
    }
    
    public List<Membership> getPendingMembershipsByClub(Long clubId) {
        return membershipRepository.findByClubIdAndStatus(clubId, MembershipStatus.PENDING);
    }
}
