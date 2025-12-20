package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByStatus(String status);
    List<Membership> findByClub(Club club);
    List<Membership> findByUser(User user);
}
