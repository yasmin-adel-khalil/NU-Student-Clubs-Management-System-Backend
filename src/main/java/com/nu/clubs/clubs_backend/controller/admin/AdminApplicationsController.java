package com.nu.clubs.clubs_backend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.dto.ApplicationRequest;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Membership;
import com.nu.clubs.clubs_backend.model.User;
import com.nu.clubs.clubs_backend.repository.ClubRepository;
import com.nu.clubs.clubs_backend.repository.MembershipRepository;
import com.nu.clubs.clubs_backend.repository.UserRepository;
import com.nu.clubs.clubs_backend.service.MembershipService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminApplicationsController {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping
    public ResponseEntity<List<Membership>> listByStatus(@RequestParam(defaultValue = "PENDING") String status) {
        return ResponseEntity.ok(membershipService.getByStatus(status));
    }

    @PostMapping
    public ResponseEntity<Membership> createPending(@RequestBody ApplicationRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found: " + req.getUserId()));
        Club club = clubRepository.findById(req.getClubId())
                .orElseThrow(() -> new NotFoundException("Club not found: " + req.getClubId()));
        Membership m = new Membership();
        m.setUser(user);
        m.setClub(club);
        m.setStatus("PENDING");
        return ResponseEntity.ok(membershipRepository.save(m));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Membership> approve(@PathVariable Long id) {
        return ResponseEntity.ok(membershipService.approve(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Membership> reject(@PathVariable Long id) {
        return ResponseEntity.ok(membershipService.reject(id));
    }
}
