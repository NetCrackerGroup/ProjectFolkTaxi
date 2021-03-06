package com.netcracker.rootsearch;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.netcracker.entities.Route;


public interface RouteFinder {

	/*
	 * adress - Адрес отправления
	 * radius - расстояние, которое пассажир готов пройти к месту отправления (в метрах)
	 * dep - время отправления, которое хочет пассажир
	 */
	HashMap<InfoAboutRoute, Route> findRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord, 
			Integer stRadius, Integer enRadius, Integer dayOfWeek, String time, Long groupId);
	
}
