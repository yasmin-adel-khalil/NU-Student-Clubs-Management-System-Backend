package com.nu.clubs.clubs_bakend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/applications")
@Validated
public class MembershipController {
    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
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
        return membershipService.findById(id)
                .map(MembershipMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MembershipResponse> createMembership(@Valid @RequestBody MembershipRequest membershipRequest) {
        Membership membership = MembershipMapper.toEntity(membershipRequest);
        Membership created = membershipService.create(membership);
        return ResponseEntity.status(HttpStatus.CREATED).body(MembershipMapper.toResponse(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
