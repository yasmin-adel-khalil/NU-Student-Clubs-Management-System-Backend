package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.BoardMember;
import com.nu.clubs.clubs_bakend.repository.BoardMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardMemberService {

    @Autowired
    private BoardMemberRepository boardMemberRepository;

    public BoardMember createBoardMember(BoardMember bm) {
        return boardMemberRepository.save(bm);
    }

    public BoardMember updateBoardMember(Long id, BoardMember details) {
        BoardMember bm = boardMemberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Board member not found: " + id));

        if (details.getEmail() != null)
            bm.setEmail(details.getEmail());
        if (details.getFirstName() != null)
            bm.setFirstName(details.getFirstName());
        if (details.getLastName() != null)
            bm.setLastName(details.getLastName());
        if (details.getPosition() != null)
            bm.setPosition(details.getPosition());
        if (details.getClub() != null)
            bm.setClub(details.getClub());
        if (details.getIsActiveBoardMember() != null)
            bm.setIsActiveBoardMember(details.getIsActiveBoardMember());
        bm.setUpdatedAt(System.currentTimeMillis());

        return boardMemberRepository.save(bm);
    }

    public BoardMember getBoardMemberById(Long id) {
        return boardMemberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Board member not found: " + id));
    }

    public List<BoardMember> getAllBoardMembers() {
        return boardMemberRepository.findAll();
    }

    public List<BoardMember> getBoardMembersByPosition(String position) {
        return boardMemberRepository.findByPosition(position);
    }

    public void deleteBoardMember(Long id) {
        if (!boardMemberRepository.existsById(id)) {
            throw new NotFoundException("Board member not found: " + id);
        }
        boardMemberRepository.deleteById(id);
    }
}
