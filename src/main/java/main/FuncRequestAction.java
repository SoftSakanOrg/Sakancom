package main;

import java.sql.*;

import static main.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncRequestAction {

    public static int RequestFlag=0;

    public static void requestAction(int ID, String answer, int testp) throws SQLException {

        RequestFlag=0;



        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        while(true) {
         Sakan.flagRequestAction=0;


            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT FLOOR_ID FROM advertisment_requests WHERE REQ_ID='"+ID+"'");
            rs= pst.executeQuery();

            if(rs.next()){
                RequestFlag=1;
                Sakan.ar.setFloorId(rs.getInt(1));
            }



            if (answer.equalsIgnoreCase("A")) {

                RequestFlag=1;


                acceptedextracted(ID, testp);


                logger.info("Request has been Approved...");
                break;
            }else if(answer.equalsIgnoreCase("B")){

                RequestFlag=1;


                rejectedextracted(ID, testp);


                logger.info("Request has been Rejected...");
                break;

            }

            else if ( answer.equalsIgnoreCase("C")) {

                RequestFlag=1;
                break;
            }
            else {
                Sakan.flagRequestAction=1;
                break;
            }
        }
    }




    private static void acceptedextracted(int ID, int testp) throws SQLException {
        Connection connection;
        PreparedStatement pst ;



        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("UPDATE FLOORS SET STATUS='Advertised' where FLOOR_ID='" + Sakan.ar.getFloorId() + "'");
            pst.executeUpdate();
        }
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("DELETE FROM advertisment_requests where REQ_ID='" + ID + "'");
            pst.executeUpdate();
        }
    }


    private static void rejectedextracted(int ID, int testp) throws SQLException {
        PreparedStatement pst;
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("DELETE FROM advertisment_requests where REQ_ID='" + ID + "'");
            pst.executeUpdate();
        }

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("DELETE FROM FLOORS where FLOOR_ID='" + Sakan.ar.getFloorId() + "'");
            pst.executeUpdate();
        }
    }

}
