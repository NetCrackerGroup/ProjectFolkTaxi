package com.netcracker.rootsearch;

import java.util.ArrayList;
import java.util.Calendar;

import com.netcracker.entities.Route;

public interface FindRoute {

	/*
	 * adress - Адрес отправления
	 * radius - расстояние, которое пассажир готов пройти к месту отправления (в метрах)
	 * dep - время отправления, которое хочет пассажир
	 */
	ArrayList<Route> findRoutes(Double Xcord, Double Ycord, Integer radius, Calendar dep);
	
}
