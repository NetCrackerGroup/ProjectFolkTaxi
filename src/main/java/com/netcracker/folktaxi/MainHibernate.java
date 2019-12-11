package com.netcracker.folktaxi;

//import com.netcracker.hibernate.EntityManagerConfiguration;

import static java.lang.System.out;


import com.netcracker.entities.Report;
import com.netcracker.repositories.NotifacationRepository;
import com.netcracker.repositories.ReportRepository;

public class MainHibernate {

	public static void main(String[] args) {
		ShowNotification();
	}



	public static void ShowNotification() {
		ReportRepository nr = new ReportRepository();
		Report report = nr.find(2l);
		out.println(report.getReportText());

	}

}
