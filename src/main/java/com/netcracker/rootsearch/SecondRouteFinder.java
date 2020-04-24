package com.netcracker.rootsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
	public HashMap<InfoAboutRoute, Route> findRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord, 
			Integer stRadius, Integer enRadius, Integer dayOfWeek, String time, Long groupId) {
		
		try {
			ArrayList<Long> ids;
	        HashMap<InfoAboutRoute, Route> res = new HashMap<InfoAboutRoute, Route>();	
	
	        List<InfoAboutRoute> infoFromQuery;
	        
	        /*
	         * AND (S.time_of_journey - CAST('19:00' AS time)) < CAST('24:00' AS interval)
			   AND (CAST('19:00' AS time) - S.time_of_journey) < CAST('24:00' AS interval)
	         */
	        if(groupId == 0) {
		      String schedQuery = " " 
	        		+ "  SELECT  S.route_id,\r\n"
	        		+ "      ST_Distance(\r\n" 
	        		+ "          R.Route_begin,\r\n" 
	        		+ "          ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography\r\n" 
	        		+ "        ) AS Distance,\r\n" 
	        		+ "        TO_CHAR(S.time_of_journey - CAST(? AS time), 'HH24:MI:SS') AS timeUntilStart"
	        		+ "  	 FROM  Schedules S,\r\n"
	        		+ "            Routes R"  
	          		+ "  	 WHERE ? & CAST(S.schedule_day AS INT) <> 0\r\n" 
	                + "        AND (S.time_of_journey - CAST( ? AS time)) < CAST( ? AS interval)\r\n"
	          		+ "        AND (CAST( ? AS time) - S.time_of_journey) < CAST( ? AS interval)\r\n"
	          		+ "        AND (R.route_id = S.route_id)"
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
			 
		      
		      infoFromQuery = template.query(schedQuery, 
		    		  new PreparedStatementSetter() {
		    	  		public void setValues(PreparedStatement preparedStatement) throws SQLException{
		    	  			
		    	  			preparedStatement.setDouble(1, stXcord);
		    	  			preparedStatement.setDouble(2, stYcord);
		    	  			
		    	  			if(time != "") {
		    	  				preparedStatement.setString(3, time);
			    	  			preparedStatement.setString(5, time);
			    	  			preparedStatement.setString(6, "01:00");
			    	  			preparedStatement.setString(7, time);
			    	  			preparedStatement.setString(8, "01:00");
		    	  			}
		    	  			else {
		    	  				preparedStatement.setString(3, "00:00");
		    	  				preparedStatement.setString(5, "00:00");
			    	  			preparedStatement.setString(6, "24:00");
			    	  			preparedStatement.setString(7, "00:00");
			    	  			preparedStatement.setString(8, "24:00");
		    	  			}
		    	  			
		    	  			preparedStatement.setInt(4, dayOfWeek);
		    	  			
		    	  			preparedStatement.setDouble(9, stXcord);
		    	  			preparedStatement.setDouble(10, stYcord);
		    	  			preparedStatement.setDouble(11, stRadius);
		    	  			preparedStatement.setDouble(12, enXcord);
		    	  			preparedStatement.setDouble(13, enYcord);
		    	  			preparedStatement.setDouble(14, enRadius);
		    	  			
		    	  		}
		      		},
		    		  new RouteInfoMapper()
		    		  ); 
	        }
	        else {
	        	String schedQuery = " " 
		        		+ "  SELECT  S.route_id,\r\n"
		        		+ "      ST_Distance(\r\n" 
		        		+ "          R.Route_begin,\r\n" 
		        		+ "          ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography\r\n" 
		        		+ "        ) AS Distance,\r\n" 
		        		+ "        TO_CHAR(S.time_of_journey - CAST(? AS time), 'HH24:MI:SS') AS timeUntilStart"
		        		+ "  	 FROM  Schedules S,\r\n"
		        		+ "            Routes R"  
		          		+ "  	 WHERE ? & CAST(S.schedule_day AS INT) <> 0\r\n" 
		                + "        AND (S.time_of_journey - CAST( ? AS time)) < CAST( ? AS interval)\r\n"
		          		+ "        AND (CAST( ? AS time) - S.time_of_journey) < CAST( ? AS interval)\r\n"
		          		+ "        AND (R.group_id = ?)\r\n"
		          		+ "        AND (R.route_id = S.route_id)\r\n"
		          		+ "        AND S.route_id IN (\r\n"
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
				 
			      
			      infoFromQuery = template.query(schedQuery, 
			    		  new PreparedStatementSetter() {
			    	  		public void setValues(PreparedStatement preparedStatement) throws SQLException{
			    	  			
			    	  			preparedStatement.setDouble(1, stXcord);
			    	  			preparedStatement.setDouble(2, stYcord);
			    	  			
			    	  			if(time != "") {
			    	  				preparedStatement.setString(3, time);
				    	  			preparedStatement.setString(5, time);
				    	  			preparedStatement.setString(6, "01:00");
				    	  			preparedStatement.setString(7, time);
				    	  			preparedStatement.setString(8, "01:00");
			    	  			}
			    	  			else {
			    	  				preparedStatement.setString(3, "00:00");
			    	  				preparedStatement.setString(5, "00:00");
				    	  			preparedStatement.setString(6, "24:00");
				    	  			preparedStatement.setString(7, "00:00");
				    	  			preparedStatement.setString(8, "24:00");
			    	  			}
			    	  			
			    	  			preparedStatement.setInt(4, dayOfWeek);
			    	  			
			    	  			preparedStatement.setLong(9, groupId);
			    	  			
			    	  			preparedStatement.setDouble(10, stXcord);
			    	  			preparedStatement.setDouble(11, stYcord);
			    	  			preparedStatement.setDouble(12, stRadius);
			    	  			preparedStatement.setDouble(13, enXcord);
			    	  			preparedStatement.setDouble(14, enYcord);
			    	  			preparedStatement.setDouble(15, enRadius);
			    	  			
			    	  		}
			      		},
			    		  new RouteInfoMapper()
			    		  ); 
	        }
		      
	          for(InfoAboutRoute info: infoFromQuery) {
	        	 
	        	  res.put(info, routeRep.findRouteByRouteId(info.getRouteId()));
	          }
	          return res;
	          
	      } catch (Exception e) {
	    	  System.out.println(e);
	          return null;
	      } 
	}

}
