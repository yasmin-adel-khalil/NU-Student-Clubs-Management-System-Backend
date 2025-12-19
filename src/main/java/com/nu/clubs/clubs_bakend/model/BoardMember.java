package com.nu.clubs.clubs_bakend.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BOARD_MEMBER")
public class BoardMember extends User {

    @Column
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "is_active_board_member")
    private Boolean isActiveBoardMember = true;

    public BoardMember() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Boolean getIsActiveBoardMember() {
        return isActiveBoardMember;
    }

    public void setIsActiveBoardMember(Boolean isActiveBoardMember) {
        this.isActiveBoardMember = isActiveBoardMember;
    }
}
