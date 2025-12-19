package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

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
