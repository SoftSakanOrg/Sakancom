package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.logger;

public class funcSelectmyfloorfunc {

    public static int myFloorFlag=0;
    public static void selectmyfloor(int building_id,int floor_id) throws SQLException {

        Sakan.flagSelectMyFloor = 0;
        myFloorFlag=0;
        Connection connection = null;
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;




            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM floors WHERE floor_id = '" +  floor_id + "' AND BUILDING_ID= '"+building_id+"'" );
            rs = pst.executeQuery();




            if (rs.next()) {
                myFloorFlag=1;
                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + "\t|\t";
               logger.info(content);
                Sakan.flagSelectMyFloor = 1;

            }
            else if(!rs.next()){
                myFloorFlag=1;
                logger.info("Please enter a valid floor ID...");

            }




        }








}