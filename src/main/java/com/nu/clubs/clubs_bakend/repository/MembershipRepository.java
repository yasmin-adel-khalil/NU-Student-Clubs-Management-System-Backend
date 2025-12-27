package com.nu.clubs.clubs_bakend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.User;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByStatus(String status);

    List<Membership> findByClub(com.nu.clubs.clubs_bakend.model.Club club);

    List<Membership> findByUser(User user);

    @Query("SELECT m FROM Membership m WHERE m.user = :user AND m.status = 'ACTIVE'")
    List<Membership> findActiveMembershipsByUser(@Param("user") User user);
}
