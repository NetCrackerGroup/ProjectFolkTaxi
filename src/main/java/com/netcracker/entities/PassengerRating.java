package com.netcracker.entities;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Passenger_Rating")
public class PassengerRating {

    @Id
    private Long userId;

    @Column(name = "Average_Mark")
    private Double averageMark;

    @Column(name = "number_of_votes")
    private Long numberOfVotes;

    public PassengerRating() {
    }

    public PassengerRating(Long userId){
        this.userId = userId;
    }
    public PassengerRating(
            @NotNull Double averageMark,
            @NotNull Long numberOfVotes
    ) {
        this.averageMark = averageMark;
        this.numberOfVotes = numberOfVotes;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }
}
