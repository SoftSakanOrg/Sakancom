package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static main.Sakan.*;
import static main.funcCheckEmail.checkemail;
import static main.funcCheckLogin.checklogin;

public class funcUsersLogin {

    public static void Login(String usertype,String email,String pass){



        if (Sakan.flag11 == 0) {






            Connection connection = null;
            PreparedStatement pst = null;
            ResultSet rs = null;



            if ( Sakan.U.getEmail().equalsIgnoreCase("1")) {
                Mainfunc();

            }
            if (!Sakan.U.getEmail().contains("@") || !Sakan.U.getEmail().contains(".")) {
                logger.info("Please enter a valid email...");
               flaglogin=1;
                tenantfunc(usertype);
          //      Login(usertype,email,pass);
            }

//            email = tempemail;

            checkemail(Sakan.U.getEmail(), 2, usertype);
            if (Sakan.flag11 == 0) {




                if (  Sakan.U.getPassword().equalsIgnoreCase("1")) {
                    Mainfunc();

                }

//                pass = temppass;
                checklogin(U.getEmail(),U.getPassword(),usertype);
            }
            Sakan.flag11 =1;
            Sakan.OnlineUser =Sakan.U.getEmail() ;

        }






    }




}
