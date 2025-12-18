package com.nu.clubs.clubs_bakend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_bakend.model.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
