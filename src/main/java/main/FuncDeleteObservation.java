package main;

import java.sql.*;

import static main.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncDeleteObservation {

    private static int flagdeleteobservations;

    public static int getFlagdeleteobservations() {
        return flagdeleteobservations;
    }

    public static void setFlagdeleteobservations(int flagdeleteobservations) {
        FuncDeleteObservation.flagdeleteobservations = flagdeleteobservations;
    }

    public static void deleteObservations(int testp) throws SQLException {

        Connection connection = null;
        PreparedStatement pst= null;


        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        if(testp==1) {
            pst = connection.prepareStatement("DELETE FROM system_observation ");
            pst.executeUpdate();
        }
        setFlagdeleteobservations(1);

        logger.info("You have successfully Deleted the Observations");
    }

}
