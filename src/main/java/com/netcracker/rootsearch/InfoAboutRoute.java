package com.netcracker.rootsearch;

public class InfoAboutRoute {

	private Long routeId;
	
	private Integer distance;
	
	private String timeUntilStart;

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getTimeUntilStart() {
		return timeUntilStart;
	}

	public void setTimeUntilStart(String timeUntil) {
		this.timeUntilStart = timeUntil;
	}
	
}
