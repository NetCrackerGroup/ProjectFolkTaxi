package com.netcracker.folktaxi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Activity {
    public void getActiveDriver() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi",
                    "postgres",
                    "root"
            );

            //PreparedStatement stmt = con.prepareStatement(" SELECT current_database();");
            PreparedStatement stmt = con.prepareStatement(
                    " SELECT J.driver_id " +
                            "FROM JOURNEY J" +
                            "GROUP BY J.driver_id" +
                            "HAVING COUNT(J.journey_id)=(SELECT MAX(VAL) FROM" +
                            "(SELECT COUNT(J1.journey_id)AS VAL " +

                            "FROM JOURNEY J1" +
                            "GROUP BY J1.driver_id)K);"
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
