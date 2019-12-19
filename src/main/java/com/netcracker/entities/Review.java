package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_generator")
    @SequenceGenerator(name = "review_id_generator", sequenceName = "review_id_seq", allocationSize = 1)
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

    public void setReviewId(Long reviewId) {this.reviewId = reviewId;}

    public void setUser(User user) {this.user = user;}

    public void setAdditionalText(String additionalText) { this.additionalText = additionalText; }

    public void setMark(Integer mark) { this.mark = mark; }

    public void setPassenger(Boolean passenger) { isPassenger = passenger; }

    public Long getReviewId() { return reviewId; }

    public User getUser() { return user; }

    public String getAdditionalText() { return additionalText; }

    public Integer getMark() { return mark; }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", user=" + user +
                ", additionalText='" + additionalText + '\'' +
                ", mark=" + mark +
                ", isPassenger=" + isPassenger +
                '}';
    }

    public Boolean getPassenger() { return isPassenger; }
}
