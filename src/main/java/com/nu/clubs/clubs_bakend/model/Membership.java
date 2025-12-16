package com.nu.clubs.clubs_bakend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "memberships")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Column
    private String status; // ACTIVE, PENDING, REJECTED, LEFT

    @Column(name = "join_date")
    private Long joinDate = System.currentTimeMillis();

    @Column(name = "leave_date")
    private Long leaveDate;

    @Column
    private String membershipType; // REGULAR, ACTIVE, HONORARY

    public Membership() {}

    public Membership(User user, Club club) {
        this.user = user;
        this.club = club;
        this.status = "PENDING";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getJoinDate() { return joinDate; }
    public void setJoinDate(Long joinDate) { this.joinDate = joinDate; }

    public Long getLeaveDate() { return leaveDate; }
    public void setLeaveDate(Long leaveDate) { this.leaveDate = leaveDate; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
}
