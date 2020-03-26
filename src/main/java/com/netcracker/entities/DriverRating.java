package com.netcracker.entities;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Driver_Rating")
public class DriverRating {

    @Id
	private Long userId;

    @Column(name = "Average_Mark")
    private Double averageMark;

    @Column(name = "number_of_votes")
    private Long numberOfVotes;

    public DriverRating() {
    }

    public DriverRating(Long userId){
        this.userId = userId;
    }
    public DriverRating(
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
