package main;

import java.sql.*;

public class funcViewObservation {

    public static int ViewObservationFlag=0;
    public static String output = "";

    public static void viewObservations() throws SQLException {
        ViewObservationFlag=0;

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("SELECT * FROM system_observation ORDER BY ID DESC" );
        rs = pst.executeQuery();


        if(!rs.next()){
            System.out.println("There are no Observations currently...");
        }
        rs = pst.executeQuery();

        while (rs.next())
        {
            ViewObservationFlag=1;
            String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Description: " + rs.getString(2) + "\t|\t";
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println(content);

        }


    }
}
