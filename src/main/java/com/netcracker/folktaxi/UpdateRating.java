package com.netcracker.folktaxi;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.*;

public class UpdateRating {
    public static void main(String[] args) {
        update(11, false, 5, "well done", 5);
    }

    public static void update(int Review_ID, Boolean Is_passenger, int Mark, String Additional_Text, int User_ID) {

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi", "postgres", "root");

            String sql = "INSERT INTO REVIEW (Review_ID, Is_passenger, Mark, Additional_Text, User_ID) Values (?, ?, ?, ?, ?)";
            float rating = 0;
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, Review_ID);
            preparedStatement.setBoolean(2, Is_passenger);
            preparedStatement.setInt(3, Mark);
            preparedStatement.setString(4, Additional_Text);
            preparedStatement.setInt(5, User_ID);
            preparedStatement.executeUpdate();


            //PreparedStatement stmt = con.prepareStatement(" SELECT current_database();");
            PreparedStatement stmt = con.prepareStatement("select try.user_id,\n" +
                    "\t\tsum(try.fls),\n" +
                    "\t\tsum(try.true)\n" +
                    "\tfrom (\n" +
                    "\t\tselect u.user_id,  \n" +
                    "\t\tcase\n" +
                    "\t\t\twhen r.Is_passenger = false then round((sum(r.mark)/count(r.mark)), 1) \n" +
                    "\t\tend\n" +
                    " \tas fls,\n" +
                    "\t\t\tcase\n" +
                    "\t\t\twhen r.Is_passenger = true then round((sum(r.mark)/count(r.mark)), 1) \n" +
                    "\t\tend\n" +
                    " \tas true\n" +
                    " from user_1 u\n" +
                    "\tleft join review r\n" +
                    "\ton u.user_id = r.user_id \n" +
                    "group by  u.user_id, r.Is_passenger\n" +
                    "\t) as try\n" +
                    "\twhere  try.user_id = "+User_ID+"\n" +
                    "\tgroup by try.user_id;");
            ResultSet rs = stmt.executeQuery();
            Statement stmtUpdate = con.createStatement();

            if (!Is_passenger) {
                while (rs.next()) { rating = rs.getInt(2); }
                stmtUpdate.executeUpdate("UPDATE Driver_Rating SET Average_Mark = "+ rating +" WHERE User_ID = "+User_ID);
            } else {
                while (rs.next()) { rating = rs.getInt(3); }
                stmtUpdate.executeUpdate("UPDATE Passenger_Rating SET Average_Mark = "+ rating +" WHERE User_ID = "+User_ID);

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

