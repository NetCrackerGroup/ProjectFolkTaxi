package com.netcracker.folktaxi;

import com.netcracker.entities.City;
import com.netcracker.repositories.CityRepository;

import static java.lang.System.out;


import com.netcracker.entities.Notification;
import com.netcracker.entities.Report;
import com.netcracker.entities.User;
import com.netcracker.repositories.NotifacationRepository;
import com.netcracker.repositories.ReportRepository;
import com.netcracker.repositories.UserRepository;

public class MainHibernate {

//	public static void main(String[] args) {
//		UserRepository userRepository = new UserRepository();
//		CityRepository cityRepository = new CityRepository();
//		City city = new City("Mosc", "43");
//		User user = new User("Kolya", "aldkjs", "8976", city);
//		userRepository.save(user);
//    EntityManagerConfiguration.getInstance().releaseResourcesAndClose();
//	}



//	public static void ShowNotification() {
//		NotifacationRepository nr = new NotifacationRepository();
//		Notification notification = nr.find(1l);
//		out.println(notification.getText());
//
//		User user = notification.getUser();
//		out.println(user.getFio());
//
//	}
//
//	public static void ShowReport(){
//		ReportRepository nr = new ReportRepository();
//		Report report = nr.find(1l);
//		out.println(report.getReportText());
//		User user = report.getUser();
//		out.println(user.getFio());
//	}
}
