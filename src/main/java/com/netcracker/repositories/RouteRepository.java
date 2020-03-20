package com.netcracker.repositories;

import com.netcracker.entities.Chat;
import com.netcracker.entities.Message;
import com.netcracker.entities.Route;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {
	
	ArrayList<Route> findRouteByCity(Long cityId);
	Route findRouteByRouteId(Long routeId);

	/*@Query("select c from Chats c where c.routeId = :routeId")
	List<Chat> findChatByRoute(@Param("routeId")Long routeId);

@Query("select m from Messages m where m.messageId in ( "+
		" select c from Chatc c where c.routeId = :routeId"+
		"limit :n")
	List<Message> findMessageByRoute(@Param("routeId")Long routeId,@Param("n")Integer n);
*/
}

