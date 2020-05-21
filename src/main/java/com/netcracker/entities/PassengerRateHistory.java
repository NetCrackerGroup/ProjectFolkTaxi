package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Passenger_Rate_History")
public class PassengerRateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passenger_rate_history_id_generator")
    @SequenceGenerator(name = "passenger_rate_history_id_generator", sequenceName = "passenger_rate_history_id_seq", allocationSize = 1)
    @Column(name = "passenger_rate_history_id")
    private Long passengerRateHistoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journey_id")
    private Journey journeyId;

    @Column(name = "rater_id")
    private Long raterId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    private User passengerId;

    @Column(name = "mark")
    private Double mark;

    public PassengerRateHistory() {
    }

    public PassengerRateHistory(
            @NotNull Journey journeyId,
            @NotNull Long raterId,
            @NotNull User passengerId,
            @NotNull Double mark
    ) {
        this.journeyId = journeyId;
        this.raterId = raterId;
        this.passengerId = passengerId;
        this.mark = mark;
    }

    public Long getRateHistoryId() {
        return passengerRateHistoryId;
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

    public User getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(User passengerId) {
        this.passengerId = passengerId;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
