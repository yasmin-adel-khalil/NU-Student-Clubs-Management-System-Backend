package com.nu.clubs.clubs_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.model.Membership;
import com.nu.clubs.clubs_backend.service.MembershipService;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping
    public ResponseEntity<List<Membership>> getAllApplications() {
        List<Membership> applications = membershipService.getAllMemberships();
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> getApplicationById(@PathVariable Long id) {
        Membership application = membershipService.getMembershipById(id);
        return ResponseEntity.ok(application);
    }

    @PostMapping
    public ResponseEntity<Membership> createApplication(@RequestBody Membership membership) {
        Membership created = membershipService.createMembership(membership);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membership> updateApplication(@PathVariable Long id, @RequestBody Membership membership) {
        Membership updated = membershipService.updateMembership(id, membership);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.noContent().build();
    }
}
