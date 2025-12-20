package com.nu.clubs.clubs_bakend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Club ID is required")
    private Long clubId;
}
