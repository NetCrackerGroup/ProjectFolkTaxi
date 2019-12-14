package com.netcracker.repositories;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.entities.User;

@Repository
public class UserRepository extends AbstractRepository<User>{

	public UserRepository() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}
}
