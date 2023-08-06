package main;

import java.sql.*;

import static main.Sakan.logger;

public class FuncAddBuilding {


    public static int selectinfoflag;
    public static int addbuildingflag;
    public static int addobservationflag;

    @SuppressWarnings("java:S1118")
    public static void addbuildingfunc(int ownerId,int testp) throws SQLException {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;







            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT username,CONTACT_NUM FROM USERS WHERE user_id='" + ownerId + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                selectinfoflag =1;
                Sakan.b.setOwnerName(rs.getString(1));
                Sakan.b.setContactNum(rs.getInt(2));

            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("INSERT INTO BUILDING(OWNER_ID,BUILDING_NAME,LOCATION,FLOORS_NUM,OWNER_NAME,CONTACT_NUM,TOTALPARTICIPANTS) VALUES" + "(?,?,?,?,?,?,?)");
            addbuildingflag =1;


                pst.setInt(1, ownerId);
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
            addobservationflag =1;
            pst.setString(1, Sakan.onlineUser + " has added a building (" + Sakan.b.getBuildingName() + ") to the system");

            if(testp==1) {
                pst.executeUpdate();
            }

            logger.info("You have successfully added your Building...");






    }








}
