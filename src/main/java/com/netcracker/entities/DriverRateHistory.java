package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Driver_Rate_History")
public class DriverRateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_rate_history_id_generator")
    @SequenceGenerator(name = "driver_rate_history_id_generator", sequenceName = "driver_rate_history_id_seq", allocationSize = 1)
    @Column(name = "driver_rate_history_id")
    private Long driverRateHistoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journey_id")
    private Journey journeyId;

    @Column(name = "rater_id")
    private Long raterId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private User driverId;

    @Column(name = "mark")
    private Double mark;

    public DriverRateHistory() {
    }

    public DriverRateHistory(
            @NotNull Journey journeyId,
            @NotNull Long raterId,
            @NotNull User driverId,
            @NotNull Double mark
    ) {
        this.journeyId = journeyId;
        this.raterId = raterId;
        this.driverId = driverId;
        this.mark = mark;
    }

    public Long getRateHistoryId() {
        return driverRateHistoryId;
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

    public User getDriverId() {
        return driverId;
    }

    public void setDriverId(User driverId) {
        this.driverId = driverId;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
