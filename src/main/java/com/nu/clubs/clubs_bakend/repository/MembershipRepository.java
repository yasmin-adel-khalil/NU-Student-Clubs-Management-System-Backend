package com.nu.clubs.clubs_bakend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.Membership.MembershipStatus;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    
    List<Membership> findByUserId(Long userId);
    
    List<Membership> findByClubId(Long clubId);
    
    Optional<Membership> findByUserIdAndClubId(Long userId, Long clubId);
    
    List<Membership> findByClubIdAndStatus(Long clubId, MembershipStatus status);
}
