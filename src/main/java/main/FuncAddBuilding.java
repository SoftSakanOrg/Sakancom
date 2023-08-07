package main;

import java.sql.*;

import static main.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncAddBuilding {


   private static int selectinfoflag;
    private static int addbuildingflag;
   private static int addobservationflag;





    public FuncAddBuilding() throws UnsupportedOperationException  {
    }



    public static int getSelectinfoflag() {
        return selectinfoflag;
    }

    public static void setSelectinfoflag(int selectinfoflag) {
        FuncAddBuilding.selectinfoflag = selectinfoflag;
    }

    public static int getAddbuildingflag() {
        return addbuildingflag;
    }

    public static void setAddbuildingflag(int addbuildingflag) {
        FuncAddBuilding.addbuildingflag = addbuildingflag;
    }

    public static int getAddobservationflag() {
        return addobservationflag;
    }

    public static void setAddobservationflag(int addobservationflag) {
        FuncAddBuilding.addobservationflag = addobservationflag;
    }



    public static void addbuildingfunc(int ownerId, int testp) throws SQLException {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;







            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT username,CONTACT_NUM FROM USERS WHERE user_id='" + ownerId + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
               setSelectinfoflag(1);
                Sakan.b.setOwnerName(rs.getString(1));
                Sakan.b.setContactNum(rs.getInt(2));

            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("INSERT INTO BUILDING(OWNER_ID,BUILDING_NAME,LOCATION,FLOORS_NUM,OWNER_NAME,CONTACT_NUM,TOTALPARTICIPANTS) VALUES" + "(?,?,?,?,?,?,?)");
           setAddbuildingflag(1);


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
          setAddobservationflag(1);
            pst.setString(1, Sakan.onlineUser + " has added a building (" + Sakan.b.getBuildingName() + ") to the system");

            if(testp==1) {
                pst.executeUpdate();
            }

            logger.info("You have successfully added your Building...");






    }








}
