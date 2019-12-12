package com.netcracker.folktaxi;

//import com.netcracker.hibernate.EntityManagerConfiguration;


import com.netcracker.repositories.UserRepository;

public class MainHibernate {

	public static void main(String[] args) {
		UserRepository userRepository = new UserRepository();
		userRepository.find(1);
	}




}
