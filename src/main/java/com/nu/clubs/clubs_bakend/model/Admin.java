package com.nu.clubs.clubs_bakend.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    @Column
    private String department;

    @Column
    private String adminLevel;

    @Column(name = "can_manage_admins")
    private Boolean canManageAdmins = false;

    @Column(name = "can_manage_clubs")
    private Boolean canManageClubs = false;

    @Column(name = "can_manage_applications")
    private Boolean canManageApplications = false;

    public Admin() {}

    public Admin(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }

    public Boolean getCanManageAdmins() { return canManageAdmins; }
    public void setCanManageAdmins(Boolean canManageAdmins) { this.canManageAdmins = canManageAdmins; }

    public Boolean getCanManageClubs() { return canManageClubs; }
    public void setCanManageClubs(Boolean canManageClubs) { this.canManageClubs = canManageClubs; }

    public Boolean getCanManageApplications() { return canManageApplications; }
    public void setCanManageApplications(Boolean canManageApplications) { this.canManageApplications = canManageApplications; }
}
