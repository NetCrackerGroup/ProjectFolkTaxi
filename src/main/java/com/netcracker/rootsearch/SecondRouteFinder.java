package com.netcracker.rootsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.netcracker.entities.Route;

@Repository
public class SecondRouteFinder implements FindRoute{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Route> findRoutes(Double Xcord, Double Ycord, Integer radius, Calendar dep) {
		
		try {
	          Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
	                  "postgres",
	                  "Palienko22"
	          );
	          
	          ArrayList<Integer> ids = new ArrayList<Integer>();
	          ArrayList<Route> res = new ArrayList<Route>();
	          
	          PreparedStatement stmt = con.prepareStatement(" " 
	        		+ "  SELECT  R.route_id\r\n"
	        		+ "  	 FROM  Routes R\r\n"  
	          		+ "  	 WHERE ST_Distance(\r\n"
	          		+ "					R.Route_begin,\r\n"
	          		+ "					ST_GeomFromText('POINT(" + Xcord.toString() + " " + Ycord.toString() + ")', 4326)\r\n"
	          	    + "					) < " + radius.toString() + "\r\n" +
	          		" ;");
	          
	          ResultSet rs = stmt.executeQuery();
	          
	          while (rs.next())
	        	  ids.add(rs.getInt(1));
	          	
	          con.close();
	          
	       
	       
	          Route curRoute;
	          
	          for(Integer id: ids) {
	        	  
	        	  curRoute = entityManager.createQuery("FROM Route"
	        	  		+ "									WHERE route_id = " + id.toString(), Route.class).getSingleResult();
	        	  
	        	  res.add(curRoute);
	          }
	      
	          return res;
	          
	      } catch (Exception e) {
	    	  
	          System.out.println(e);
	          
	          
	          return null;
	      } 
	}

}
