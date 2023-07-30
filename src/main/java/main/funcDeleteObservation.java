package main;

import java.sql.*;

public class funcDeleteObservation {

    public static void deleteObservations() throws SQLException {

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("DELETE FROM system_observation " );
        pst.executeUpdate();

        System.out.println("You have successfully Deleted the Observations");
    }

}
