package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rate_History")
public class RateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_history_id_generator")
    @SequenceGenerator(name = "rate_history_id_generator", sequenceName = "rate_history_id_seq", allocationSize = 1)
    @Column(name = "rate_history_id")
    private Long rateHistoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journey_id")
    private Journey journeyId;

    @Column(name = "rater_id")
    private Long raterId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "mark")
    private Double mark;

    @Column(name = "user_is_driver")
    private Boolean userIsDriver;

    public RateHistory() {
    }

    public RateHistory(
            @NotNull Journey journeyId,
            @NotNull Long raterId,
            @NotNull User userId,
            @NotNull Double mark,
            @NotNull Boolean userIsDriver
    ) {
        this.journeyId = journeyId;
        this.raterId = raterId;
        this.userId = userId;
        this.mark = mark;
        this.userIsDriver = userIsDriver;
    }

    public Long getRateHistoryId() {
        return rateHistoryId;
    }

    public Journey getJourneyId() {
        return journeyId;
    }

    public void setJourney(Journey journeyId) {
        this.journeyId = journeyId;
    }

    public Long getRaterId() {
        return raterId;
    }

    public void setRaterId(Long raterId) {
        this.raterId = raterId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
