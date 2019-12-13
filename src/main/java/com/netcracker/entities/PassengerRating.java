package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Passenger_Rating")
public class PassengerRating {

	@Id
    private Long id;

	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "User_id")
    private User passRatingUser;

    public User getPassRatingUser() {
        return passRatingUser;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PassengerRating{" +
                "id=" + id +
                ", passRatingUser=" + passRatingUser +
                ", averageMark=" + averageMark +
                '}';
    }

    @Column(name = "Average_Mark")
    private double averageMark;

    public PassengerRating(){}
    public PassengerRating(
            @NotNull  User passRatingUser,
            @NotNull  double averageMark
    ) {
        this.passRatingUser = passRatingUser;
        this.averageMark = averageMark;
    }
}
