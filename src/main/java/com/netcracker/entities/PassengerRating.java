package com.netcracker.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Passenger_Rating")
public class PassengerRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User passRatingUser;

    public User getPassRatingUser() {
        return passRatingUser;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public int getId() {
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
