package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.User;
import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    List<Membership> findByUserAndActive(User user, Boolean active);

    @Query("SELECT m FROM Membership m WHERE m.user = :user AND m.active = true")
    List<Membership> findActiveMembershipsByUser(@Param("user") User user);
}
