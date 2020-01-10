package com.netcracker.services;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceTest {
	
	@Autowired
	private RouteService routeServ;
	
	@MockBean
	private RouteRepository routeRep;
	
	@Test
	public void getRoutesByCityIdTest() {
		
		Long cityId = new Long(0);
		
		Mockito.doReturn(new ArrayList<Route>())
				.when(routeRep)
				.findRouteByCity(cityId);
	
		ArrayList<Route> routes = new ArrayList<Route>();
		ArrayList<Route> result = routeServ.getRoutesByCityId(cityId);
		
		Assert.assertEquals(true, true);
		Assert.assertEquals(result, routes);
		
		Mockito.verify(routeRep, Mockito.times(1)).findRouteByCity(cityId);
	}
	
	
}
