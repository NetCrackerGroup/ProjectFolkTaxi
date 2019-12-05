package com.netcracker.folktaxi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Класс дающий представление об токсичности пользователя народного такси
 */
public class Toxicity {

    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Toxicity(int user_id){
        this.user_id = user_id;
    }

    public int getCountReport() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi",
                                                        "postgres",
                                                        "root"
                                                        );

            //PreparedStatement stmt = con.prepareStatement(" SELECT current_database();");
            PreparedStatement stmt = con.prepareStatement(
                    " SELECT COUNT(*)" +
                            "FROM REPORT " +
                            "WHERE USER_ID = " + this.user_id + ";"
            );
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            con.close();
            return count;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
}
