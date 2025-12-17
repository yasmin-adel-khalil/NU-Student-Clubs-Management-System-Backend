package com.nu.clubs.clubs_bakend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubResponse {
    private Long id;
    private String name;
    private String description;
    private String president;
    private String email;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
