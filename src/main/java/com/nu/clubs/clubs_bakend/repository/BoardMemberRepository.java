package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.BoardMember;
import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {
    Optional<BoardMember> findByEmail(String email);
    List<BoardMember> findByClub(Club club);
    List<BoardMember> findByClubAndIsActiveBoardMember(Club club, Boolean isActive);
    List<BoardMember> findByPosition(String position);
}
