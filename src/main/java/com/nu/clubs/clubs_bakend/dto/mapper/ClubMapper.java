package com.nu.clubs.clubs_bakend.dto.mapper;

import com.nu.clubs.clubs_bakend.dto.ClubResponse;
import com.nu.clubs.clubs_bakend.model.Club;

public final class ClubMapper {
    private ClubMapper() {}

    public static ClubResponse toResponse(Club club) {
        if (club == null) return null;
        return new ClubResponse(
                club.getId(),
                club.getName(),
                club.getDescription(),
                club.getPresident(),
                club.getEmail(),
                club.getCategory(),
                club.getCreatedAt(),
                club.getUpdatedAt()
        );
    }
}
