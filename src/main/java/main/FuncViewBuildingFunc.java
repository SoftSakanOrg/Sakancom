package main;

import java.sql.*;

import static Runner.Sakan.*;
@SuppressWarnings("java:S1118")
public class FuncViewBuildingFunc {

    private static int viewfloorflag;

    public static int getViewfloorflag() {
        return viewfloorflag;
    }

    public static void setViewfloorflag(int viewfloorflag) {
        FuncViewBuildingFunc.viewfloorflag = viewfloorflag;
    }

    public static void viewBuildingFunc(int buildingId, String view1, int testp) throws SQLException {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;



            if (view1.equalsIgnoreCase("A")) {


                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("SELECT * FROM floors WHERE  building_id = " + buildingId + "  ");
                    rs = pst.executeQuery();
                    if( !rs.next()){
                        setViewfloorflag(1);
                        logger.info("No floors available currently");
                    }
                    rs = pst.executeQuery();


                    while (rs.next()) {
                        setViewfloorflag(1);
                        String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t availability: "+ rs.getString(3)+ "\t|\t Price: "+ rs.getInt(4)+ "\t|\t Services: "+ rs.getString(5)+ "\t|\t Participants: "+ rs.getInt(6)+ "\t|\t Max_Participants: "+ rs.getInt(7)+ "\t|\t BedroomsNum: "+ rs.getInt(8)+ "\t|\t BathroomsNum: "+ rs.getInt(9)+ "\t|\t Balcony: "+ rs.getInt(10)+ "\t|\t Status: "+ rs.getString(11);
                        logger.info(content);


                    }




            }


            else if(view1.equalsIgnoreCase("E")){

                setViewfloorflag(1);

                if(testp==1) {
                    mainfunc();
                }
            }
        }




}
