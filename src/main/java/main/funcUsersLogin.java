package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static main.Sakan.*;
import static main.funcCheckEmail.checkemail;
import static main.funcCheckLogin.checklogin;

public class funcUsersLogin {

    public static void Login(String usertype,String email,String pass) throws SQLException {



        if (Sakan.flag11 == 0) {






            Connection connection = null;
            PreparedStatement pst = null;
            ResultSet rs = null;



            if ( Sakan.u.getEmail().equalsIgnoreCase("1")) {
                mainfunc();

            }
            if (!Sakan.u.getEmail().contains("@") || !Sakan.u.getEmail().contains(".")) {
                logger.info("Please enter a valid email...");
               flaglogin=1;
                tenantfunc(usertype);

            }



            checkemail(Sakan.u.getEmail(), 2, usertype);
            if (Sakan.flag11 == 0) {




                if (  Sakan.u.getPassword().equalsIgnoreCase("1")) {
                    mainfunc();

                }

//                pass = temppass;
                checklogin(u.getEmail(), u.getPassword(),usertype);
            }
            Sakan.flag11 =1;
            Sakan.onlineUser =Sakan.u.getEmail() ;

        }






    }




}
