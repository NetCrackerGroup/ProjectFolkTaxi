package com.netcracker.rootsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netcracker.entities.Route;

@Repository
public class BasicRouteFinder implements FindRoute {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	public static Double distFrom(Double lat1, Double lng1, Double lat2, Double lng2) {
	    Double earthRadius = new Double(6371); // (6371.0 kilometers)
	    Double dLat = Math.toRadians(lat2-lat1);
	    Double dLng = Math.toRadians(lng2-lng1);
	    Double sindLat = Math.sin(dLat / 2);
	    Double sindLng = Math.sin(dLng / 2);
	    Double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	            * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    Double dist = earthRadius * c;

	    return dist;
	} 
	
	@Override
	public HashMap<InfoAboutRoute, Route> findRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord,
			Integer stRadius, Integer enRadius, Integer dayOfWeek, String time) {
		return null;
		/*
		ArrayList<Route> all = new ArrayList<Route>(entityManager.createQuery("from  Route", Route.class).getResultList());
		Double rad = new Double(radius);
		rad /= 1000;
		ArrayList<Route> res = new ArrayList<Route>();
		
		for(Route rt: all) {
			if(distFrom(Xcord, Ycord, rt.getRouteBegin().getX(), rt.getRouteBegin().getY()) <= rad) {
				res.add(rt);
			}
		}
		
		return res;
	//	return new ArrayList<Route>();
	/*
		String req = ""
				+ "from  Route "
				+ "where routeId = cos(0)";
		
		return entityManager.createQuery(req, Route.class).getResultList();
		
		
		/*
		return entityManager.createQuery(""
				+ "from  Route r"
				+ "where sqrt( (r.routeBegin.getX() - :Xparam) * (r.routeBegin.getX() - :Xparam) "
				+ "          + (r.routeBegin.getY() - :Yparam) * (r.routeBegin.getY() - :Yparam) ) <= :Radparam",
				Route.class).getResultList();
	    
		
		
	/*	
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		Query qr = session.createQuery(""
				+ "from  Route r"
				+ "where sqrt( (r.routeBegin.getX() - :Xparam) * (r.routeBegin.getX() - :Xparam) "
				+ "          + (r.routeBegin.getY() - :Yparam) * (r.routeBegin.getY() - :Yparam) ) <= :Radparam");
		
		qr.setParameter("Xparam", Xcord.toString());
		qr.setParameter("Yparam", Ycord.toString());
		Double rad = (double) radius;
		qr.setParameter("Radparam", rad.toString());
		
		ArrayList<Route> res = new ArrayList<Route>(qr.list());
		
		
		return res;
		
		
	/*	try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi", "", "");
			ArrayList<Route> resRoutes = new ArrayList<Route>();
			Route temp;
			
			// Конвертирование Route_Begin в Double
			
			PreparedStatement stmt = con.prepareStatement(" " +
					"SELECT  r.*,\r\n" + 
	          		"  FROM  Route r\r\n" +
					"  WHERE ( SQRT(POWER(" + Xcord.toString() + " - r.Route_Begin, 2.0)\r\n" +
				    "             + POWER(" + Ycord.toString() + " - r.Route_End, 2.0)) <= " + (double) radius +" )\r\n" +
	          		";");
	          
	        ResultSet rs = stmt.executeQuery();
			
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "           " + rs.getString(2));
              
                temp = new Route();
                temp.set
            }		
          
            con.close();
		
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		
	*/	
	}

}
