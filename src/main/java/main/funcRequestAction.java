package main;

import java.sql.*;
import java.util.Scanner;

public class funcRequestAction {

    public static void requestAction(int ID,String answer) throws SQLException {



        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        while(true) {
         Sakan.flagRequestAction=0;

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT FLOOR_ID FROM advertisment_requests WHERE REQ_ID='"+ID+"'");
            rs= pst.executeQuery();

            if(rs.next()){
                Sakan.ar.setFloorId(rs.getInt(1));
            }





//     String picture = null;
            if (answer.equalsIgnoreCase("A")) {



                try {



                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("UPDATE FLOORS SET STATUS='Advertised' where FLOOR_ID='"+Sakan.ar.getFloorId()+"'");
                    pst.executeUpdate();

                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("DELETE FROM advertisment_requests where REQ_ID='"+ID+"'");
                    pst.executeUpdate();





                } catch (SQLException e) {
                    e.printStackTrace();

                }
                System.out.println("Request has been Approved...");
                break;
            }else if(answer.equalsIgnoreCase("B")){

                try {


                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("DELETE FROM advertisment_requests where REQ_ID='"+ID+"'");
                    pst.executeUpdate();

                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("DELETE FROM FLOORS where FLOOR_ID='"+Sakan.ar.getFloorId()+"'");
                    pst.executeUpdate();




                } catch (SQLException e) {
                    e.printStackTrace();

                }

                System.out.println("Request has been Rejected...");
                break;

            }

            else if ( answer.equalsIgnoreCase("C")) {
                break;
            }
            else {
                Sakan.flagRequestAction=1;
                break;
            }
        }
    }

}
