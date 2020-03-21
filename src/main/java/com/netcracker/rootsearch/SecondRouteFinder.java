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
	
	private static final String driverClassName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/folktaxi";
    private static final String dbUsername = "postgres";
    private static final String dbPassword = "root";
	
	public static DriverManagerDataSource getDataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName(driverClassName);
		  dataSource.setUrl(url);
		  dataSource.setUsername(dbUsername);
		  dataSource.setPassword(dbPassword);
		 
		  return dataSource;
	}
	
	@Override
	public List<Route> findRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord, 
			Integer stRadius, Integer enRadius, Integer dayOfWeek) {
		
		try {
			
			/*
	          Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
	                  "postgres",
	                  "Palienko22"
	          );
	          
	          ArrayList<Long> ids = new ArrayList<Long>();
	          ArrayList<Route> res = new ArrayList<Route>();
	          
	          PreparedStatement stmt = con.prepareStatement(" " 
	        		+ "  SELECT  R.route_id\r\n"
	        		+ "  	 FROM  Routes R\r\n"  
	          		+ "  	 WHERE ST_Distance(\r\n"
	          		+ "					R.route_begin,\r\n"
	          		+ "					ST_GeomFromText('POINT(" + stXcord.toString() + " " + stYcord.toString() + ")', 4326)\r\n"
	          	    + "					) < " + stRadius.toString() + "\r\n" 
		          	+ "            ST_Distance(\r\n" 
	          		+ "                 R.route_end,\r\n " 
	          	    + "                 ST_GeomFromText('Point(" + enXcord.toString() + " " + enYcord.toString() + ")', 4326)\r\n"
	          	    + "                 ) < " + enRadius.toString() + "\r\n"
	          		+ " ;");
	          
	          ResultSet rs = stmt.executeQuery();
	          
	          while (rs.next())
	        	  ids.add(rs.getLong(1));
	          	
	          con.close();
	        */  
	          
			
			ArrayList<Long> ids;
	        ArrayList<Route> res = new ArrayList<Route>();	
	
	        
	     //   PGpoint stPoint = new PGpoint(stXcord, stYcord);
	     //   PGpoint enPoint = new PGpoint(enXcord, enYcord);
	      //  query.setObject(1, myPoint); 
	        
	        
	 
	        String query = " " 
	        		+ "  SELECT  R.route_id\r\n"
	        		+ "  	 FROM  Routes R\r\n"  
	          		+ "  	 WHERE ST_Distance(\r\n"
	          		+ "					R.Route_begin,\r\n"
	          		+ "					ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography\r\n"
	          	    + "					) < ? AND\r\n" 
		          	+ "            ST_Distance(\r\n" 
	          		+ "                 R.route_end,\r\n " 
	          	    + "                 ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography\r\n"
	          	    + "                 ) < ?\r\n"
	            	+ " ;";
	        
	        
		      // Убрать CAST
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
	        
	        
			  DataSource dataSource;
		      dataSource = getDataSource();
		      JdbcTemplate template = new JdbcTemplate(dataSource);
			 
		      /*
		      List<Long> idsFromQuery = template.query(query, 
		    		  new PreparedStatementSetter() {
		    	  		public void setValues(PreparedStatement preparedStatement) throws SQLException{
		    	  			preparedStatement.setObject(1, stPoint);
		    	  			preparedStatement.setDouble(2, stRadius);
		    	  			preparedStatement.setObject(3, enPoint);
		    	  			preparedStatement.setDouble(4, enRadius);
		    	  			
		    	  		}
		      		},
		    		  new LongMapper()
		    		  ); 
		      */
		      
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
