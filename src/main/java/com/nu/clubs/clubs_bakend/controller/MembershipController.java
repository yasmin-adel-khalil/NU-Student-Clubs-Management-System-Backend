package com.nu.clubs.clubs_bakend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nu.clubs.clubs_bakend.dto.MembershipRequest;
import com.nu.clubs.clubs_bakend.dto.MembershipResponse;
import com.nu.clubs.clubs_bakend.dto.mapper.MembershipMapper;
import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.service.MembershipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @PostMapping
    public ResponseEntity<MembershipResponse> createMembership(@RequestBody MembershipRequest request) {
        Membership membership = new Membership();
        membership.setUserId(request.getUserId());
        membership.setClubId(request.getClubId());
        
        Membership created = membershipService.createMembership(membership);
        return ResponseEntity.status(HttpStatus.CREATED).body(MembershipMapper.toResponse(created));
    }

    @GetMapping
    public List<MembershipResponse> getAllMemberships() {
        return membershipService.findAll()
                .stream()
                .map(MembershipMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipResponse> getMembershipById(@PathVariable Long id) {
        return ResponseEntity.ok(MembershipMapper.toResponse(membershipService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
