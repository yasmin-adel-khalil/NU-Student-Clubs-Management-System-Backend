package com.nu.clubs.clubs_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_backend.model.BoardMember;
import com.nu.clubs.clubs_backend.model.Club;

import java.util.List;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {
    List<BoardMember> findByClub(Club club);

    List<BoardMember> findByPosition(String position);
}
