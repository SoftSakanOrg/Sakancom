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

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM advertisment_requests WHERE req_id = '" +  Sakan.ar.getReqId() + "'" );
            rs = pst.executeQuery();

            if (rs.next()) {

                String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Building_Name: " + rs.getString(2) + "\t|\t Owner_name: " + rs.getString(3) + "\t|\t Contact_Number: " + rs.getInt(4) + "\t|\t" + rs.getInt(5) + "\t|\t Floor_ID: "  +rs.getInt(6)+"\t|\t";
                System.out.println(content);
                Scanner sf=new Scanner(System.in);
                System.out.println("Do you want to accept this request or deleter it? ");
                System.out.println("(A)Accept    (B)delete    (C)Go back   ");
                String answer = sf.next();
                requestAction(rs.getInt(1),answer,testp);
            }
            else if(!rs.next()){
                System.out.println("Please enter a valid request ID...");

            }




        } catch (SQLException e) {
            e.printStackTrace();

        }




    }
}
