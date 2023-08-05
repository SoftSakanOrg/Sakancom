package main;

import java.sql.*;

import static main.Sakan.logger;

public class funcAddBuilding {

    public static int selectInfoFlag;
    public static int addBuildingFlag;
    public static int addObservationFlag;
    public static void addbuildingfunc(int owner_ID,int testp) throws SQLException {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;







            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT username,CONTACT_NUM FROM USERS WHERE user_id='" + owner_ID + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                selectInfoFlag=1;
                Sakan.b.setOwnerName(rs.getString(1));
                Sakan.b.setContactNum(rs.getInt(2));

            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("INSERT INTO BUILDING(OWNER_ID,BUILDING_NAME,LOCATION,FLOORS_NUM,OWNER_NAME,CONTACT_NUM,TOTALPARTICIPANTS) VALUES" + "(?,?,?,?,?,?,?)");
            addBuildingFlag=1;


                pst.setInt(1, owner_ID);
                pst.setString(2, Sakan.b.getBuildingName());
                pst.setString(3, Sakan.b.getLocation());
                pst.setInt(4, 0);
                pst.setString(5, Sakan.b.getOwnerName());
                pst.setInt(6, Sakan.b.getContactNum());
                pst.setInt(7, 0);
        if(testp==1) {
                pst.executeUpdate();
            }

            pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");
            addObservationFlag=1;
            pst.setString(1, Sakan.onlineUser + " has added a building (" + Sakan.b.getBuildingName() + ") to the system");

            if(testp==1) {
                pst.executeUpdate();
            }

            logger.info("You have successfully added your Building...");






    }








}
