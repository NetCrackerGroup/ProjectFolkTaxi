package com.netcracker.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Driver_Rating")
public class DriverRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User driveRatingUser;

    @Column(name = "Average_Mark")
    private double averageMark;

    public DriverRating(){}
    public DriverRating(
            @NotNull User driveRatingUser,
            @NotNull double averageMark
    ) {
        this.driveRatingUser = driveRatingUser;
        this.averageMark = averageMark;
    }

    public void setDriveRatingUser(User driveRatingUser) {
        this.driveRatingUser = driveRatingUser;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }
}
