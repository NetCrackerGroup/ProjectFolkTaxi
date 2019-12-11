package com.netcracker.folktaxi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindMess {

	public static void getNMesseges(int n) {
		try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi",
                    "postgres",
                    "root"
            );

            PreparedStatement stmt = con.prepareStatement(
                    " SELECT M.* " +
                            "FROM MESSAGE M," +
                    			 "CHAT C" +
                    			 "ROUTE R" +
                            "ORDER BY R.ROUTE_BEGIN" +
                            "LIMIT " + n		
            );
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                System.out.println(rs.getString(1));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
}
