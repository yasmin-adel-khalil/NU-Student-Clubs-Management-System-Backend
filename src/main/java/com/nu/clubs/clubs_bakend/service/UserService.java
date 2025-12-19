package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.dto.UserProfileResponse;
import com.nu.clubs.clubs_bakend.model.User;
import com.nu.clubs.clubs_bakend.model.Membership;
import com.nu.clubs.clubs_bakend.model.BestMember;
import com.nu.clubs.clubs_bakend.repository.UserRepository;
import com.nu.clubs.clubs_bakend.repository.MembershipRepository;
import com.nu.clubs.clubs_bakend.repository.BestMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private BestMemberRepository bestMemberRepository;

    public UserProfileResponse getUserProfile(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !user.getActive()) {
            throw new RuntimeException("User not found or inactive");
        }

        // Get active memberships
        List<Membership> memberships = membershipRepository.findActiveMembershipsByUser(user);
        List<String> clubNames = memberships.stream()
                .map(m -> m.getClub().getName())
                .collect(Collectors.toList());
        Integer clubsCount = clubNames.size();

        // Check if best member
        List<BestMember> bestMembers = bestMemberRepository.findBestMembersByUser(user);
        Boolean isBestMember = !bestMembers.isEmpty();

        return new UserProfileResponse(
                user.getFullName(),
                user.getEmail(),
                user.getProfilePicture(),
                user.getPhoneNumber(),
                user.getDepartment(),
                user.getUserType().toString(),
                user.getActive(),
                clubNames,
                clubsCount,
                isBestMember
        );
    }
}
