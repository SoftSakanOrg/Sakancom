package main;

import java.sql.*;

import static main.Sakan.logger;

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
            output="No Observations currently";
            logger.info("output");
        }
        rs = pst.executeQuery();

        while (rs.next())
        {
            ViewObservationFlag=1;
            String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Description: " + rs.getString(2) + "\t|\t";
            logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            logger.info(content);

        }


    }
}
