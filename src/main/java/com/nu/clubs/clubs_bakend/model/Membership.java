package com.nu.clubs.clubs_bakend.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
	private Long id;
	private Long userId;
	private Long clubId;
	private MembershipStatus status;
	private LocalDateTime appliedAt;
	private LocalDateTime processedAt;
}
