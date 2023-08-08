package main;

import Runner.Sakan;

import java.sql.*;

import static Runner.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncSelectmyfloorfunc {

    private static int myFloorFlag=0;

    public static int getMyFloorFlag() {
        return myFloorFlag;
    }

    public static void setMyFloorFlag(int myFloorFlag) {
        FuncSelectmyfloorfunc.myFloorFlag = myFloorFlag;
    }

    public static void selectmyfloor(int buildingId, int floorId) throws SQLException {

        Sakan.flagSelectMyFloor = 0;
        setMyFloorFlag(0);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;





            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM floors WHERE floor_id = '" +  floorId + "' AND BUILDING_ID= '"+buildingId+"'" );
            rs = pst.executeQuery();




            if (rs.next()) {
                setMyFloorFlag(1);
                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + "\t|\t";
               logger.info(content);
                Sakan.flagSelectMyFloor = 1;

            }
            else if(!rs.next()){
                setMyFloorFlag(1);
                logger.info("Please enter a valid floor ID...");

            }




        }








}