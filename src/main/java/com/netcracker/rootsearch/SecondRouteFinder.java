package com.netcracker.rootsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.postgresql.geometric.PGpoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;

@Repository
public class SecondRouteFinder implements FindRoute{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private RouteRepository routeRep;
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<Route> findRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord, 
			Integer stRadius, Integer enRadius, Integer dayOfWeek) {
		
		try {
			ArrayList<Long> ids;
	        ArrayList<Route> res = new ArrayList<Route>();	
	
		      String schedQuery = " " 
	        		+ "  SELECT  S.route_id\r\n"
	        		+ "  	 FROM  Schedules S\r\n"  
	          		+ "  	 WHERE ? & CAST(S.schedule_day AS INT) <> 0"
	          		+ "        AND S.route_id IN ("
	          		+ "  		SELECT  R.route_id\r\n"
	        		+ "  	 		FROM  Routes R\r\n"  
	          		+ "  	 		WHERE ST_Distance(\r\n"
	          		+ "						R.Route_begin,\r\n"
	          		+ "						ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography\r\n"
	          	    + "						) < ? AND\r\n" 
		          	+ "            		  ST_Distance(\r\n" 
	          		+ "                 	R.route_end,\r\n " 
	          	    + "                 	ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography\r\n"
	          	    + "                   ) < ?\r\n"
	        		+ ") "
	            	+ " ;";
	        
	        
		      JdbcTemplate template = new JdbcTemplate(dataSource);
			 
		      
		      List<Long> idsFromQuery = template.query(schedQuery, 
		    		  new PreparedStatementSetter() {
		    	  		public void setValues(PreparedStatement preparedStatement) throws SQLException{
		    	  			preparedStatement.setInt(1, dayOfWeek);
		    	  			preparedStatement.setDouble(2, stXcord);
		    	  			preparedStatement.setDouble(3, stYcord);
		    	  			preparedStatement.setDouble(4, stRadius);
		    	  			preparedStatement.setDouble(5, enXcord);
		    	  			preparedStatement.setDouble(6, enYcord);
		    	  			preparedStatement.setDouble(7, enRadius);
		    	  			
		    	  		}
		      		},
		    		  new LongMapper()
		    		  ); 
		      
		      
		      
		      ids = new ArrayList<Long>(idsFromQuery);
		      
		      
		      
		      
	          for(Long id: ids) {
	        	 
	        	  res.add(routeRep.findRouteByRouteId(id));
	          }
	      
	          return res;
	          
	      } catch (Exception e) {
	    	  
	          System.out.println(e);
	          
	          
	          return null;
	      } 
	}

}
