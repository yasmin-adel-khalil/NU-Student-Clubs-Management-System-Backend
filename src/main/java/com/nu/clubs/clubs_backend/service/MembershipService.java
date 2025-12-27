package com.nu.clubs.clubs_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Membership;
import com.nu.clubs.clubs_backend.repository.MembershipRepository;

import java.util.List;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public Membership getMembershipById(Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found: " + id));
    }

    public Membership createMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    public Membership updateMembership(Long id, Membership details) {
        Membership m = membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found: " + id));
        if (details.getStatus() != null)
            m.setStatus(details.getStatus());
        return membershipRepository.save(m);
    }

    public void deleteMembership(Long id) {
        if (!membershipRepository.existsById(id))
            throw new NotFoundException("Membership not found: " + id);
        membershipRepository.deleteById(id);
    }

    public List<Membership> getByStatus(String status) {
        return membershipRepository.findByStatus(status);
    }

    public Membership approve(Long id) {
        Membership m = membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found: " + id));
        m.setStatus("APPROVED");
        return membershipRepository.save(m);
    }

    public Membership reject(Long id) {
        Membership m = membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found: " + id));
        m.setStatus("REJECTED");
        return membershipRepository.save(m);
    }
}
