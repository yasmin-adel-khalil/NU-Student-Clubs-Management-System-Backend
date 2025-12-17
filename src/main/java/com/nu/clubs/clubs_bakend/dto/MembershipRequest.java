package com.nu.clubs.clubs_bakend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipRequest {
    private Long userId;
    private Long clubId;
}
