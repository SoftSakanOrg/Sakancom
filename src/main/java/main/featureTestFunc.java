package main;

import java.sql.*;
import java.util.Scanner;

public class featureTestFunc {

    public static int flag=1;

    public static void login(Tenant tenant) {

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT EMAIL,PASSWORD FROM TENANTS WHERE EMAIL = '" + tenant.getEmail() + "' AND PASSWORD = '"+ tenant.getPassword() +"'");
            rs = pst.executeQuery();

            System.out.println(tenant.getEmail()+tenant.getPassword());
            if(rs.next()){

             System.out.println("hello");
                featureTestFunc.flag=1;
//                String tempE =  rs.getString(1) ;
//                String tempP = rs.getString(2);
////                if(!tempE.equals(null) && !tempP.equals(null)){
////                    System.out.println("Logged in successfully");
////                    Sakan.flag1 = 1;
               } else  {
                System.out.println("hey");
                featureTestFunc.flag=0;
            }

//
//            }
//





        } catch (SQLException e) {
            e.printStackTrace();

        }




    }
}
