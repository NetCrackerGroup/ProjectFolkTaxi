package com.netcracker.folktaxi;

import com.netcracker.hibernate.EntityManagerConfiguration;
import com.netcracker.entities.City;
import com.netcracker.repositories.CityRepository;

public class MainHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerConfiguration.getInstance().releaseResourcesAndClose();
	    
	}
	
	

}
