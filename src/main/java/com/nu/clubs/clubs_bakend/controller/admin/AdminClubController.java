package com.nu.clubs.clubs_bakend.controller.admin;

import com.nu.clubs.clubs_bakend.model.Club;
import com.nu.clubs.clubs_bakend.repository.ClubRepository;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/clubs")
@CrossOrigin(origins = "*")
public class AdminClubController {

    @Autowired
    private ClubRepository clubRepository;

    @PostMapping
    public ResponseEntity<Club> create(@RequestBody Club c) {
        return ResponseEntity.ok(clubRepository.save(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> update(@PathVariable Long id, @RequestBody Club d) {
        Club c = clubRepository.findById(id).orElseThrow(() -> new NotFoundException("Club not found: " + id));
        if (d.getName() != null)
            c.setName(d.getName());
        if (d.getDescription() != null)
            c.setDescription(d.getDescription());
        if (d.getEmail() != null)
            c.setEmail(d.getEmail());
        if (d.getCategory() != null)
            c.setCategory(d.getCategory());
        if (d.getPresident() != null)
            c.setPresident(d.getPresident());
        return ResponseEntity.ok(clubRepository.save(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!clubRepository.existsById(id))
            throw new NotFoundException("Club not found: " + id);
        clubRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Club>> list() {
        return ResponseEntity.ok(clubRepository.findAll());
    }
}
