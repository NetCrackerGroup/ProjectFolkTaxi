package com.netcracker.rootsearch;

import java.util.Calendar;
import java.util.List;

import com.netcracker.entities.Route;


public interface FindRoute {

	/*
	 * adress - Адрес отправления
	 * radius - расстояние, которое пассажир готов пройти к месту отправления (в метрах)
	 * dep - время отправления, которое хочет пассажир
	 */
	List<Route> findRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord, 
			Integer stRadius, Integer enRadius, Integer dayOfWeek);
	
}
