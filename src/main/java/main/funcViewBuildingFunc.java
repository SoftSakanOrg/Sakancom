package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcAddFloor.addfloor;
import static main.funcOwner.ownerfunc;
import static main.funcSelectmyfloorfunc.selectmyfloor;

public class funcViewBuildingFunc {

    public static void viewBuildingFunc(int owner_ID,int building_ID, String view1) throws SQLException {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;



            if (view1.equalsIgnoreCase("A")) {

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("SELECT * FROM floors WHERE  building_id = " + building_ID + "  ");
                    rs = pst.executeQuery();
                    if( !rs.next()){
                        logger.info("No floors available currently");
                    }
                    rs = pst.executeQuery();


                    while (rs.next()) {

                        String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t availability: "+ rs.getString(3)+ "\t|\t Price: "+ rs.getInt(4)+ "\t|\t Services: "+ rs.getString(5)+ "\t|\t Participants: "+ rs.getInt(6)+ "\t|\t Max_Participants: "+ rs.getInt(7)+ "\t|\t BedroomsNum: "+ rs.getInt(8)+ "\t|\t BathroomsNum: "+ rs.getInt(9)+ "\t|\t Balcony: "+ rs.getInt(10)+ "\t|\t Status: "+ rs.getString(11);
                        logger.info(content);


                    }


                } catch (SQLException e) {
                    e.printStackTrace();

                }


            }

            else if(view1.equalsIgnoreCase("D")){
                ownerfunc("OWNERS",owner_ID);
            }
            else if(view1.equalsIgnoreCase("E")){
                Mainfunc();
            }
        }




}
