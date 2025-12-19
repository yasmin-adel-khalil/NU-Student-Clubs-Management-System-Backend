package com.nu.clubs.clubs_bakend.controller.admin;

import com.nu.clubs.clubs_bakend.dto.BoardMemberRequest;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.BoardMember;
import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.repository.ClubRepository;
import com.nu.clubs.clubs_bakend.service.BoardMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/board-members")
@CrossOrigin(origins = "*")
public class AdminBoardMemberController {

    @Autowired
    private BoardMemberService service;

    @Autowired
    private ClubRepository clubRepository;

    @PostMapping
    public ResponseEntity<BoardMember> create(@RequestBody BoardMemberRequest request) {
        BoardMember bm = new BoardMember();
        applyRequest(bm, request, true);
        return ResponseEntity.ok(service.createBoardMember(bm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardMember> update(@PathVariable Long id, @RequestBody BoardMemberRequest request) {
        BoardMember existing = service.getBoardMemberById(id);
        applyRequest(existing, request, false);
        return ResponseEntity.ok(service.updateBoardMember(id, existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBoardMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BoardMember>> list() {
        return ResponseEntity.ok(service.getAllBoardMembers());
    }

    private void applyRequest(BoardMember target, BoardMemberRequest request, boolean isCreate) {
        if (request.getEmail() != null) {
            target.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            target.setPassword(request.getPassword());
        }
        if (request.getFirstName() != null) {
            target.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            target.setLastName(request.getLastName());
        }
        if (request.getPosition() != null) {
            target.setPosition(request.getPosition());
        }
        if (request.getActive() != null) {
            target.setIsActiveBoardMember(request.getActive());
            target.setActive(request.getActive());
        }
        if (request.getClubId() != null) {
            Club club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new NotFoundException("Club not found: " + request.getClubId()));
            target.setClub(club);
        } else if (isCreate) {
            throw new NotFoundException("Club is required for the board member");
        }
    }
}
