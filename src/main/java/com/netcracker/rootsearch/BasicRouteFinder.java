package com.netcracker.rootsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.netcracker.entities.Route;
import com.netcracker.hibernate.utils.HibernateSessionFactory;

public class BasicRouteFinder implements FindRoute {

	@Override
	public ArrayList<Route> findRoutes(Double Xcord, Double Ycord, Integer radius, Calendar depart) {
		
		//return new ArrayList<Route>();
		 
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		
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
