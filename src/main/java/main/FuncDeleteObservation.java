package main;

import java.sql.*;

import static main.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncDeleteObservation {

    public static int  flagDeleteObservations=0;
    public static void deleteObservations(int testp) throws SQLException {
       flagDeleteObservations=0;
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        if(testp==1) {
            pst = connection.prepareStatement("DELETE FROM system_observation ");
            pst.executeUpdate();
        }
    flagDeleteObservations=1;

        logger.info("You have successfully Deleted the Observations");
    }

}
