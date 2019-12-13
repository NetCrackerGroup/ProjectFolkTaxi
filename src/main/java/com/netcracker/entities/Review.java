package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Review_ID")
    private Long reviewId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_ID")
    private User user;

    @Column(name = "Additional_Text")
    private String additionalText;

    @Column(name = "Mark")
    @Size(min = 0, max = 5)
    private Integer mark;

    @Column(name = "Is_passenger")
    private Boolean isPassenger;

    public Review() {
    }

    public Review(User user, String additionalText, Integer mark, Boolean isPassenger) {
        this.additionalText = additionalText;
        this.isPassenger = isPassenger;
        this.user = user;
        this.mark = mark;
    }
}
