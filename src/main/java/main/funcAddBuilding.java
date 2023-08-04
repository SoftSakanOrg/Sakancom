package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.logger;
import static main.funcOwner.ownerfunc;

public class funcAddBuilding {

    public static void addbuildingfunc(int owner_ID) throws SQLException {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        // Sakan.U.setUsersID(0);


        //  Sakan.F.setFurnitureStatus("forsale");







        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT username,CONTACT_NUM FROM USERS WHERE user_id='" + owner_ID + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                Sakan.B.setOwnerName(rs.getString(1));
                Sakan.B.setContactNum(rs.getInt(2));
            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("INSERT INTO BUILDING(OWNER_ID,BUILDING_NAME,LOCATION,FLOORS_NUM,OWNER_NAME,CONTACT_NUM,TOTALPARTICIPANTS) VALUES" + "(?,?,?,?,?,?,?)");

            pst.setInt(1,  owner_ID);
            pst.setString(2, Sakan.B.getBuildingName());
            pst.setString(3, Sakan.B.getLocation());
            pst.setInt(4,0);
            pst.setString(5, Sakan.B.getOwnerName());
            pst.setInt(6, Sakan.B.getContactNum());
            pst.setInt(7, 0);

            pst.executeUpdate();

            pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

            pst.setString(1, Sakan.OnlineUser + " has added a building (" + Sakan.B.getBuildingName() + ") to the system");

            pst.executeUpdate();


            logger.info("You have successfully added your Building...");


        } catch (SQLException e) {
            e.printStackTrace();

        }

        // Sakan.flag2 = 1;
      // ownerfunc("OWNERS",owner_ID);

    }








}
