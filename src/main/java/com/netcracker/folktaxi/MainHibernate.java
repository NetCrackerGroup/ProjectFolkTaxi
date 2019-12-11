package com.netcracker.folktaxi;

//import com.netcracker.hibernate.EntityManagerConfiguration;

import com.netcracker.entities.City;
import com.netcracker.entities.PassengerRating;
import com.netcracker.entities.User;
import com.netcracker.hibernate.EntityManagerConfiguration;
import com.netcracker.repositories.CityRepository;
import com.netcracker.repositories.GroupRepository;
import com.netcracker.repositories.PassengerRatingRepository;
import com.netcracker.repositories.UserRepository;

public class MainHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CityRepository cItyRepository = new CityRepository();
		UserRepository userRepository = new UserRepository();
		PassengerRatingRepository passengerRatingRepository = new PassengerRatingRepository();
		City city = new City("213","Non");
		cItyRepository.create(city);
		System.out.println(city.getCityId());
//		cItyRepository.save(city);
		User user = new User("kjsd234", "123","@@", city);
//		userRepository.save(user);
//		System.out.println();
//		userRepository.save(user);

//		passengerRatingRepository.find(1);
//
//		GroupRepository gr = new GroupRepository();

		EntityManagerConfiguration.getInstance().releaseResourcesAndClose();




	    
	}

}
