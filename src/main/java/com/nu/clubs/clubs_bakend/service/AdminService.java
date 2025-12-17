package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.model.Admin;
import com.nu.clubs.clubs_bakend.repository.AdminRepository;
import com.nu.clubs.clubs_bakend.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found with id: " + id));
        
        if (adminDetails.getEmail() != null) admin.setEmail(adminDetails.getEmail());
        if (adminDetails.getFirstName() != null) admin.setFirstName(adminDetails.getFirstName());
        if (adminDetails.getLastName() != null) admin.setLastName(adminDetails.getLastName());
        if (adminDetails.getDepartment() != null) admin.setDepartment(adminDetails.getDepartment());
        if (adminDetails.getAdminLevel() != null) admin.setAdminLevel(adminDetails.getAdminLevel());
        if (adminDetails.getCanManageAdmins() != null) admin.setCanManageAdmins(adminDetails.getCanManageAdmins());
        if (adminDetails.getCanManageClubs() != null) admin.setCanManageClubs(adminDetails.getCanManageClubs());
        if (adminDetails.getCanManageApplications() != null) admin.setCanManageApplications(adminDetails.getCanManageApplications());
        admin.setUpdatedAt(System.currentTimeMillis());
        
        return adminRepository.save(admin);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found with id: " + id));
    }

    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found with id: " + id));
        admin.setActive(false);
        adminRepository.save(admin);
    }

    public void permanentlyDeleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new NotFoundException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }
}
