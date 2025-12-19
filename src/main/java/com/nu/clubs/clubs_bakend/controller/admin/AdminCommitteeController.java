package com.nu.clubs.clubs_bakend.controller.admin;

import com.nu.clubs.clubs_bakend.dto.CommitteeRequest;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.model.Committee;
import com.nu.clubs.clubs_bakend.repository.ClubRepository;
import com.nu.clubs.clubs_bakend.service.CommitteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/committees")
@CrossOrigin(origins = "*")
public class AdminCommitteeController {

    @Autowired
    private CommitteeService service;

    @Autowired
    private ClubRepository clubRepository;

    @PostMapping
    public ResponseEntity<Committee> create(@RequestBody CommitteeRequest request) {
        Committee committee = new Committee();
        applyRequest(committee, request, true);
        return ResponseEntity.ok(service.createCommittee(committee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Committee> update(@PathVariable Long id, @RequestBody CommitteeRequest request) {
        Committee existing = service.getCommitteeById(id);
        applyRequest(existing, request, false);
        return ResponseEntity.ok(service.updateCommittee(id, existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCommittee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Committee>> list() {
        return ResponseEntity.ok(service.getAllCommittees());
    }

    private void applyRequest(Committee target, CommitteeRequest request, boolean isCreate) {
        if (request.getName() != null) {
            target.setName(request.getName());
        }
        if (request.getDescription() != null) {
            target.setDescription(request.getDescription());
        }
        if (request.getClubId() != null) {
            Club club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new NotFoundException("Club not found: " + request.getClubId()));
            target.setClub(club);
        } else if (isCreate) {
            throw new NotFoundException("Club is required for the committee");
        }
    }
}
