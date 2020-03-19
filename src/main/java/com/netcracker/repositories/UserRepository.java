package com.netcracker.repositories;

//import org.springframework.data.repository.CrudRepository;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    List<User> findAllByRoutes(Route route);
    List<User> findAllByGroups(Group group);
    User findUserByFio(String username);
    User findUserByUserId(Long userId);
}
