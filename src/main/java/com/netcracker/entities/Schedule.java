package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Schedules")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_id_generator")
	@SequenceGenerator(name = "schedule_id_generator", sequenceName = "schedule_id_seq", allocationSize = 1)
	@Column(name = "schedule_id")
	private Long scheduleId;

	@NotNull
	@Column(name = "schedule_day")
	private Integer scheduleDay;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "route_id")
	private Route routeId;

	@Temporal(TemporalType.TIME)
	@Column(name = "time_of_journey")
	private Date timeOfJourney;

	public Schedule() {

	}

	public Schedule(@NotNull
					@Size(min = 1, max = 20)
							Integer scheduleDay,
					Date timeOfJourney) {
		this.scheduleDay = scheduleDay;
		this.timeOfJourney = timeOfJourney;

	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getScheduleDay() {
		return scheduleDay;
	}

	public void setScheduleDay(Integer scheduleDay) {
		this.scheduleDay = scheduleDay;
	}

	public Route getRouteId() {
		return routeId;
	}

	public void setRouteId(Route routeId) {
		this.routeId = routeId;
	}

	public Date getTimeOfJourney() {
		return timeOfJourney;
	}

	public void setTimeOfJourney(Date timeOfJourney) {
		this.timeOfJourney = timeOfJourney;
	}


	@Override
	public int hashCode() {
		return Objects.hash(scheduleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Schedule schedule = (Schedule) obj;
		return scheduleId.equals(schedule.scheduleId);
	}

	@Override
	public String toString() {
		return "Schedule{" +
				"scheduleId=" + scheduleId +
				", scheduleDay=" + scheduleDay +
				", routeId=" + routeId +
				", time=" + timeOfJourney +
				"}";
	}



}