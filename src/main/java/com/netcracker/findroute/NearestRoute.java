package com.netcracker.findroute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import java.sql.Connection;

/**
 * Class which storage seek optimal route method
 *
 */
public class NearestRoute {
	
	/**
	 * Method which seek optimal route for user
	 * @param xStart x - coordinate of starting point
	 * @param yStart y - coordinate of starting point
	 * @param xFinish x - coordinate of ending point
	 * @param yFinish y - coordinate of ending point
	 */
  static void seekNearestRoute(Integer xStart, Integer yStart, Integer xFinish, Integer yFinish, Integer cityId) {
	  try {
          Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi",
                  "",
                  ""
          );
          
          PreparedStatement stmt = con.prepareStatement(" select  r.driver_id,\r\n" + 
          		"		(sqrt(\r\n" + 
          		"				(\r\n" + 
          		"					(to_number(substring(r.route_begin, '\\((\\d+), '), '999999') - " + xStart.toString() + ") *\r\n" + 
          		"						(to_number(substring(r.route_begin, '\\((\\d+), '), '999999') - " + xStart.toString() + ")\r\n" + 
          		"				) +\r\n" + 
          		"					(\r\n" + 
          		"						(to_number(substring(r.route_begin, ', (\\d+)\\)'), '999999') - " + yStart.toString() + ")) *\r\n" + 
          		"							(to_number(substring(r.route_begin, ', (\\d+)\\)'), '999999') - " + yStart.toString() + ")\r\n" + 
          		"					) + sqrt(\r\n" + 
          		"				(\r\n" + 
          		"					(to_number(substring(r.route_end, '\\((\\d+), '), '999999') - " + xFinish.toString() + ") *\r\n" + 
          		"						(to_number(substring(r.route_end, '\\((\\d+), '), '999999') - " + xFinish.toString() + ")\r\n" + 
          		"				) +\r\n" + 
          		"					(\r\n" + 
          		"						(to_number(substring(r.route_end, ', (\\d+)\\)'), '999999') - " + yFinish.toString() + ")) *\r\n" + 
          		"							(to_number(substring(r.route_end, ', (\\d+)\\)'), '999999') - " + yFinish.toString() + ")\r\n" + 
          		"					)\r\n" + 
          		"				)\r\n" + 
          		"					as range_a\r\n" + 
          		"  from  route r\r\n" + 
          		"  where r.city_id = " + cityId.toString() +
          		"  order by	range_a \r\n" + 
          		";");
          
          ResultSet rs = stmt.executeQuery();
          
          System.out.println(rs.getMetaData().getColumnName(1) + "   " +rs.getMetaData().getColumnName(2));
          
          while (rs.next())
          	System.out.println(rs.getInt(1) + "           " + rs.getString(2));
          
          con.close();
          
      } catch (Exception e) {
    	  
          System.out.println(e);
          
      } 
  }
	
  
  static void seekNearestRouteLanLon(Double xStart, Double yStart, Double xFinish, Double yFinish, Double cityId) {
	  try {
          Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi",
                  "",
                  ""
          );
          
          PreparedStatement stmt = con.prepareStatement(" select  r.driver_id,\r\n" + 
          		"		(sqrt(\r\n" + 
          		"				(\r\n" + 
          		"					(to_number(substring(r.route_begin, '\\((\\d+), '), '999999') - " + xStart.toString() + ") *\r\n" + 
          		"						(to_number(substring(r.route_begin, '\\((\\d+), '), '999999') - " + xStart.toString() + ")\r\n" + 
          		"				) +\r\n" + 
          		"					(\r\n" + 
          		"						(to_number(substring(r.route_begin, ', (\\d+)\\)'), '999999') - " + yStart.toString() + ")) *\r\n" + 
          		"							(to_number(substring(r.route_begin, ', (\\d+)\\)'), '999999') - " + yStart.toString() + ")\r\n" + 
          		"					) + sqrt(\r\n" + 
          		"				(\r\n" + 
          		"					(to_number(substring(r.route_end, '\\((\\d+), '), '999999') - " + xFinish.toString() + ") *\r\n" + 
          		"						(to_number(substring(r.route_end, '\\((\\d+), '), '999999') - " + xFinish.toString() + ")\r\n" + 
          		"				) +\r\n" + 
          		"					(\r\n" + 
          		"						(to_number(substring(r.route_end, ', (\\d+)\\)'), '999999') - " + yFinish.toString() + ")) *\r\n" + 
          		"							(to_number(substring(r.route_end, ', (\\d+)\\)'), '999999') - " + yFinish.toString() + ")\r\n" + 
          		"					)\r\n" + 
          		"				)\r\n" + 
          		"					as range_a\r\n" + 
          		"  from  route r\r\n" + 
          		"  where r.city_id = " + cityId.toString() +
          		"  order by	range_a \r\n" + 
          		";");
          
          ResultSet rs = stmt.executeQuery();
          
          System.out.println(rs.getMetaData().getColumnName(1) + "   " +rs.getMetaData().getColumnName(2));
          
          while (rs.next())
          	System.out.println(rs.getInt(1) + "           " + rs.getString(2));
          
          con.close();
          
      } catch (Exception e) {
    	  
          System.out.println(e);
          
      } 
  }

}
