package main;

import java.sql.*;

import static main.Sakan.*;

public class funcViewBuildingFunc {

    public static int viewFloorFlag;
    public static void viewBuildingFunc(int owner_ID,int building_ID, String view1,int testp) throws SQLException {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;



            if (view1.equalsIgnoreCase("A")) {


                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("SELECT * FROM floors WHERE  building_id = " + building_ID + "  ");
                    rs = pst.executeQuery();
                    if( !rs.next()){
                        viewFloorFlag=1;
                        logger.info("No floors available currently");
                    }
                    rs = pst.executeQuery();


                    while (rs.next()) {
                       viewFloorFlag=1;
                        String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t availability: "+ rs.getString(3)+ "\t|\t Price: "+ rs.getInt(4)+ "\t|\t Services: "+ rs.getString(5)+ "\t|\t Participants: "+ rs.getInt(6)+ "\t|\t Max_Participants: "+ rs.getInt(7)+ "\t|\t BedroomsNum: "+ rs.getInt(8)+ "\t|\t BathroomsNum: "+ rs.getInt(9)+ "\t|\t Balcony: "+ rs.getInt(10)+ "\t|\t Status: "+ rs.getString(11);
                        logger.info(content);


                    }




            }


            else if(view1.equalsIgnoreCase("E")){

                    viewFloorFlag = 1;

                if(testp==1) {
                    mainfunc();
                }
            }
        }




}
