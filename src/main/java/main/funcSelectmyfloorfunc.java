package main;

import java.sql.*;
import java.util.Scanner;

public class funcSelectmyfloorfunc {

    public static void selectmyfloor(int building_id){

       Sakan.flagSelectMyFloor = 0;
        Connection connection = null;
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;



        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM floors WHERE floor_id = '" +  Sakan.H.getHouseId() + "' AND BUILDING_ID= '"+building_id+"'" );
            rs = pst.executeQuery();




            if (rs.next()) {

                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + "\t|\t";
                System.out.println(content);
                Sakan.flagSelectMyFloor = 1;

            }
            else if(!rs.next()){
                System.out.println("Please enter a valid floor ID...");

            }




        } catch (SQLException e) {
            e.printStackTrace();

        }





    }


}
