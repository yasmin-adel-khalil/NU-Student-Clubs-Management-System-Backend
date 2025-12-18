package com.nu.clubs.clubs_bakend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nu.clubs.clubs_bakend.model.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
