package com.nu.clubs.clubs_bakend.repository;

import com.nu.clubs.clubs_bakend.model.BoardMember;
import com.nu.clubs.clubs_bakend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {
    List<BoardMember> findByClub(Club club);

    List<BoardMember> findByPosition(String position);
}
