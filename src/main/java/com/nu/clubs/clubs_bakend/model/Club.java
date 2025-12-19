package com.nu.clubs.clubs_bakend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clubs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "description")
    private String description;

    @Column(nullable = false)
    private String president;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String category;

    @Column(name = "number_of_members")
    private Integer numberOfMembers;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "logo")
    private String logo;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "president_id")
    private Long presidentId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
