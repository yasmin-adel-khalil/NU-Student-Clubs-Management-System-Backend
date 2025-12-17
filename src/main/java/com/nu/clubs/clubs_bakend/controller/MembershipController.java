package com.nu.clubs.clubs_bakend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.service.MembershipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class MembershipController {
    
    private final MembershipService membershipService;
    
    @PostMapping
    public ResponseEntity<Membership> applyToClub(@RequestParam Long userId, @RequestParam Long clubId) {
        Membership membership = membershipService.applyToClub(userId, clubId);
        return ResponseEntity.status(HttpStatus.CREATED).body(membership);
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<Membership> approveMembership(@PathVariable Long id) {
        Membership membership = membershipService.approveMembership(id);
        return ResponseEntity.ok(membership);
    }
    
    @PutMapping("/{id}/reject")
    public ResponseEntity<Membership> rejectMembership(@PathVariable Long id) {
        Membership membership = membershipService.rejectMembership(id);
        return ResponseEntity.ok(membership);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdrawMembership(@PathVariable Long id) {
        membershipService.withdrawMembership(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable Long id) {
        Membership membership = membershipService.getMembershipById(id);
        return ResponseEntity.ok(membership);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Membership>> getMembershipsByUser(@PathVariable Long userId) {
        List<Membership> memberships = membershipService.getMembershipsByUser(userId);
        return ResponseEntity.ok(memberships);
    }
    
    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Membership>> getMembershipsByClub(@PathVariable Long clubId) {
        List<Membership> memberships = membershipService.getMembershipsByClub(clubId);
        return ResponseEntity.ok(memberships);
    }
    
    @GetMapping("/club/{clubId}/pending")
    public ResponseEntity<List<Membership>> getPendingMembershipsByClub(@PathVariable Long clubId) {
        List<Membership> memberships = membershipService.getPendingMembershipsByClub(clubId);
        return ResponseEntity.ok(memberships);
    }
}
