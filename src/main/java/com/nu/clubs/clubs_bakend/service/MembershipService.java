package com.nu.clubs.clubs_bakend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;

@Service
public class MembershipService {
    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    public Optional<Membership> findById(Long id) {
        return membershipRepository.findById(id);
    }
}
