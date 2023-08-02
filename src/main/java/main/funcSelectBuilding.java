package main;

import java.sql.*;
import java.util.Scanner;


import static main.Sakan.logger;
import static main.funcViewBuildingFunc.viewBuildingFunc;

public class funcSelectBuilding {

    public static void selectbuilding(int owner_id){
        Scanner sc=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        System.out.print("Enter the Building ID: ");
        while (true) {
            Sakan.B.setBuildingId(sc.nextInt());

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst = connection.prepareStatement("SELECT * FROM building WHERE building_id = '" + Sakan.B.getBuildingId() + "' AND  OWNER_ID = '" + owner_id + "'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    Sakan.B.setBuildingName(rs.getString(3));
                    logger.info("\t\t\t          ★★★★★★★★   " + Sakan.B.getBuildingName() + "  ★★★★★★★★            \t\t\t");
                    String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Location: " + rs.getString(4) + "\t|\t Floors_num: " + rs.getInt(5) + "\t|\t Owner_name: " + rs.getString(6) + "\t|\t Contact_num: " + rs.getInt(7);
                    logger.info(content);
                    Sakan.ar.setBuildingName(Sakan.B.getBuildingName());
                    Sakan.ar.setOwnerName(rs.getString(6));
                    Sakan.ar.setContactNumber(rs.getInt(7));


                    viewBuildingFunc(rs.getInt(2), rs.getInt(1));

                } else if (!rs.next()) {

                    logger.info("Invalid building ID...");
                    break;

                }


            } catch (SQLException e) {
                e.printStackTrace();

            }

        }

    }






}
