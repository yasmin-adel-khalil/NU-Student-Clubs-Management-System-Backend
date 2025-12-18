package com.nu.clubs.clubs_bakend.dto.mapper;

import com.nu.clubs.clubs_bakend.dto.MembershipRequest;
import com.nu.clubs.clubs_bakend.dto.MembershipResponse;
import com.nu.clubs.clubs_bakend.model.Membership;

public final class MembershipMapper {
    private MembershipMapper() {}

    public static MembershipResponse toResponse(Membership membership) {
        if (membership == null) return null;
        return new MembershipResponse(
                membership.getId(),
                membership.getUserId(),
                membership.getClubId(),
                membership.getJoinedAt()
        );
    }

    public static Membership toEntity(MembershipRequest request) {
        if (request == null) return null;
        Membership membership = new Membership();
        membership.setUserId(request.getUserId());
        membership.setClubId(request.getClubId());
        return membership;
    }
}
