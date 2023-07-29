package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.adminFunc.adminfunc;
import static main.usersLoginFunc.Login;

public class checkLoginFunc {

    public static void checklogin(String email, String pass, String usertype){


        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        PreparedStatement tst= null;




        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT EMAIL,PASSWORD,USER_TYPE FROM USERS WHERE EMAIL = '" + email + "' AND PASSWORD = '" + pass + "'  AND user_type = '" + usertype + "'");
            rs = pst.executeQuery();



            if (rs.next()) {

                String tempE = rs.getString(1);
                String tempP = rs.getString(2);
                String tempU = rs.getString(3);
                if (!tempE.equals(null) && !tempP.equals(null) && !tempU.equals(null)) {
                    System.out.println("Logged in successfully");

                    Sakan.flag1 = 1;
                }

            } else if (!rs.next()) {
                Scanner sc=new Scanner(System.in);
                System.out.println("Invalid username or email");


                flaglogin=1;
                tenantfunc(usertype);


            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT USER_ID FROM USERS WHERE EMAIL = '" + email + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                U.setUsersID(rs.getInt(1));

                if(usertype.equalsIgnoreCase("OWNERS")) {
                    Sakan.OnlineUser=U.getEmail();
                    ownerfunc("OWNERS", U.getUsersID());
                }

                else if(usertype.equalsIgnoreCase("ADMIN")) {
                    Sakan.OnlineUser=U.getEmail();

                    adminfunc("ADMIN", U.getUsersID());
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }


//

    }

}
