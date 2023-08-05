package main;

import java.sql.*;
import java.util.Scanner;


import static main.Sakan.logger;

public class FuncSelectBuilding {

    public static int selectBuildingFlag;
    public static int invalidFlag;
    public static void selectbuilding(int building_id,int owner_id) throws SQLException {
        Sakan.flagSelectBuilding=0;
        Scanner sc=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;




                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst = connection.prepareStatement("SELECT * FROM building WHERE building_id = '" + building_id + "' AND  OWNER_ID = '" + owner_id + "'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    invalidFlag=0;
                    selectBuildingFlag=1;
                    Sakan.flagSelectBuilding=1;
                    Sakan.b.setBuildingId(rs.getInt(1));
                    Sakan.b.setOwnerId(rs.getInt(2));
                    Sakan.b.setBuildingName(rs.getString(3));
                    logger.info("\t\t\t          ★★★★★★★★   " + Sakan.b.getBuildingName() + "  ★★★★★★★★            \t\t\t");
                    String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Location: " + rs.getString(4) + "\t|\t Floors_num: " + rs.getInt(5) + "\t|\t Owner_name: " + rs.getString(6) + "\t|\t Contact_num: " + rs.getInt(7);
                    logger.info(content);
                    Sakan.ar.setBuildingName(Sakan.b.getBuildingName());
                    Sakan.ar.setOwnerName(rs.getString(6));
                    Sakan.ar.setContactNumber(rs.getInt(7));

                } else if (!rs.next()) {
                    selectBuildingFlag=1;
                    invalidFlag=1;
                    logger.info("Invalid building ID...");


                }






    }






}
