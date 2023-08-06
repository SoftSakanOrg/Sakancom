package main;

import java.sql.*;

import static main.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncViewRequest {

    public static int ViewRequestFlag;


    public static void viewRequests() throws SQLException {
        ViewRequestFlag=0;

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("SELECT * FROM advertisment_requests" );
        rs = pst.executeQuery();

       if(!rs.next()){

           ViewRequestFlag=1;
           logger.info("No requests currently");
        }
        rs = pst.executeQuery();



        while (rs.next())
        {

            ViewRequestFlag=1;
            String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Building_Name: " + rs.getString(2) + "\t|\t Owner_name: " + rs.getString(3) + "\t|\t Contact_Number: " + rs.getInt(4) + "\t|\t" + rs.getInt(5) + "\t|\t Floor_ID: " +rs.getInt(6)+"\t|\t";
            logger.info(content);

        }
    }

}
