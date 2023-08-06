package main;

import java.sql.*;

import static main.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncViewBuilding {

    public static int flagViewBuilding=0;
    public static void viewbuilding(int ownerID,int testp) throws SQLException {

        flagViewBuilding=0;

        Connection connection = null;
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;


            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM building WHERE OWNER_ID= '"+ownerID+"'");
            rs = pst.executeQuery();


            while (rs.next()) {
                flagViewBuilding=1;
                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t Building name: " + rs.getString(3)  + "\t|\t Location: " + rs.getString(4) +  "\t|\t FloorsNum: " + rs.getInt(5) + "\t|\t Owner: " + rs.getString(6)  + "\t|\t ContactNumber:" + rs.getInt(7)   + "\t|\t TotalParticipants:" + rs.getInt(8);
                logger.info(content);
            }


        if(testp==1) {

          //  ownerfunc("OWNERS", ownerID);
        }
    }



}
