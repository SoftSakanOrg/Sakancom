package main;

import java.sql.*;
import java.util.Scanner;

import static main.funcRequestAction.requestAction;

public class funcSelectRequest {

    public static void selectRequest(int testp){
        Scanner sc=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        Sakan.flagSelectRequest = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM advertisment_requests WHERE req_id = '" +  Sakan.ar.getReqId() + "'" );
            rs = pst.executeQuery();

             if(!rs.next()){
                System.out.println("Please enter a valid request ID...");

            }
            rs = pst.executeQuery();
            if (rs.next()) {
                 Sakan.flagSelectRequest = 1;
                String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Building_Name: " + rs.getString(2) + "\t|\t Owner_name: " + rs.getString(3) + "\t|\t Contact_Number: " + rs.getInt(4) + "\t|\t" + rs.getInt(5) + "\t|\t Floor_ID: "  +rs.getInt(6)+"\t|\t";
                System.out.println(content);
               Sakan.ar.setReqId(rs.getInt(1));
             }





        } catch (SQLException e) {
            e.printStackTrace();

        }




    }
}
