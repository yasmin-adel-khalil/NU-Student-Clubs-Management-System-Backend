package com.nu.clubs.clubs_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Committee;
import com.nu.clubs.clubs_backend.repository.CommitteeRepository;

import java.util.List;

@Service
public class CommitteeService {

    @Autowired
    private CommitteeRepository committeeRepository;

    public Committee createCommittee(Committee c) {
        return committeeRepository.save(c);
    }

    public Committee updateCommittee(Long id, Committee details) {
        Committee c = committeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Committee not found: " + id));
        if (details.getName() != null)
            c.setName(details.getName());
        if (details.getDescription() != null)
            c.setDescription(details.getDescription());
        if (details.getClub() != null)
            c.setClub(details.getClub());
        return committeeRepository.save(c);
    }

    public Committee getCommitteeById(Long id) {
        return committeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Committee not found: " + id));
    }

    public List<Committee> getAllCommittees() {
        return committeeRepository.findAll();
    }

    public void deleteCommittee(Long id) {
        if (!committeeRepository.existsById(id))
            throw new NotFoundException("Committee not found: " + id);
        committeeRepository.deleteById(id);
    }

    public Committee deactivateCommittee(Long id) {
        Committee c = committeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Committee not found: " + id));
        c.setIsActive(false);
        return committeeRepository.save(c);
    }

    public Committee addMemberToCommittee(Long committeeId, Long userId) {
        Committee c = committeeRepository.findById(committeeId)
                .orElseThrow(() -> new NotFoundException("Committee not found: " + committeeId));
        // Logic to add member would go here (requires User repository)
        return c;
    }

    public Committee removeMemberFromCommittee(Long committeeId, Long userId) {
        Committee c = committeeRepository.findById(committeeId)
                .orElseThrow(() -> new NotFoundException("Committee not found: " + committeeId));
        // Logic to remove member would go here (requires User repository)
        return c;
    }
}
