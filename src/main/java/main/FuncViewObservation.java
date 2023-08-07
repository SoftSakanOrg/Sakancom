package main;

import java.sql.*;

import static main.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncViewObservation {

    private static int viewObservationFlag=0;

    public static int getViewObservationFlag() {
        return viewObservationFlag;
    }

    public static void setViewObservationFlag(int viewObservationFlag) {
        FuncViewObservation.viewObservationFlag = viewObservationFlag;
    }

    public static void viewObservations() throws SQLException {
        setViewObservationFlag(0);

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("SELECT * FROM system_observation ORDER BY ID DESC" );
        rs = pst.executeQuery();


        if(!rs.next()){
            setViewObservationFlag(1);

            logger.info("No Observations currently");
        }
        rs = pst.executeQuery();

        while (rs.next())
        {
            setViewObservationFlag(1);
            String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Description: " + rs.getString(2) + "\t|\t";
            logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            logger.info(content);

        }


    }
}
