package main;

import java.sql.*;

import static main.funcOwner.ownerfunc;

public class funcViewBuilding {

    public static void viewbuilding(int ownerID){

        Connection connection = null;
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM building WHERE OWNER_ID= '"+ownerID+"'");

            rs = pst.executeQuery();

            while (rs.next()) {

                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t Building name: " + rs.getString(3)  + "\t|\t Location: " + rs.getString(4) +  "\t|\t FloorsNum: " + rs.getInt(5) + "\t|\t Owner: " + rs.getString(6)  + "\t|\t ContactNumber:" + rs.getInt(7)   + "\t|\t TotalParticipants:" + rs.getInt(8);
                System.out.println(content);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }
        ownerfunc("OWNERS",ownerID);

    }



}
