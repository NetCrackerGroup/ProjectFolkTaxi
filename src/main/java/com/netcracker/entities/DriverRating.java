package com.netcracker.entities;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Driver_Rating")
public class DriverRating {
	
	@Id
	private Long id;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "User_id")
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
