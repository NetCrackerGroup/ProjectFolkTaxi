package com.netcracker.folktaxi;

//import com.netcracker.hibernate.EntityManagerConfiguration;

import com.netcracker.entities.City;
import com.netcracker.entities.DriverRating;
import com.netcracker.entities.PassengerRating;
import com.netcracker.entities.User;
import com.netcracker.hibernate.EntityManagerConfiguration;
import com.netcracker.repositories.*;

public class MainHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CityRepository cItyRepository = new CityRepository();
		UserRepository userRepository = new UserRepository();
		DriverRatingRepository driverRatingRepository = new DriverRatingRepository();
//		City city = new City("2134","Non");
//		cItyRepository.find(1);
//		System.out.println(city.getCityId());
//		cItyRepository.save(city);
//		User user = new User("kjsd234", "123","@@", cItyRepository.find(1));
//		userRepository.save(user);
		DriverRating driverRating = new DriverRating(userRepository.find(1), 4.5);
		driverRatingRepository.save(driverRating);
//		DriverRating driverRating = new DriverRating(userRepository.find(1), 4.3);
//		System.out.println();
//		userRepository.save(user);

//		passengerRatingRepository.find(1);
//
//		GroupRepository gr = new GroupRepository();

		EntityManagerConfiguration.getInstance().releaseResourcesAndClose();




	    
	}

}
