package com.netcracker.folktaxi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.lang.System.out;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
    
        //System.out.println( "Hello FolkTaxi!" );
        /*try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/folktaxi",
                    "postgres",
                    "root"
            );

            //PreparedStatement stmt = con.prepareStatement(" SELECT current_database();");
            PreparedStatement stmt = con.prepareStatement(" SELECT COUNT(*)" +
                                                                    "FROM REPORT");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            	System.out.println(rs.getString(1));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }*/

        Toxicity t = new Toxicity(2);
        out.println(t.getCountReport());
    }
  
}

	