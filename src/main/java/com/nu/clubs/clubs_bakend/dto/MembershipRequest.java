package com.nu.clubs.clubs_bakend.dto;

import lombok.Data;

@Data
public class MembershipRequest {
    private Long userId;
    private Long clubId;
}
