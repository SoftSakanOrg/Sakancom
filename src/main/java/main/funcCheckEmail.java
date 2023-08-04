package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcUsersLogin.Login;

public class funcCheckEmail {


    public static void checkemail(String email, int func, String usertype ){  //func 1 forSign up // func2 for Login


        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT EMAIL FROM USERS WHERE EMAIL = '" + email + "' "  );
            rs = pst.executeQuery();

            if(rs.next()){

                String tempE =  rs.getString(1);

                if(Sakan.flag1==0) {

                    if(!tempE.equals(null)){

                        if (func == 1) {
                            logger.info("This email already exists..");
                            Signup(usertype);
                        }
                    }


                }
            }
            else if(!rs.next()){
                if (flag11==0) {
                    if (func == 2) {
                        logger.info("a user with that email doesn't exists..");

                        Scanner sc=new Scanner(System.in);

                        logger.info("(1) To go back to main menu");

                        logger.info("Enter your email: ");
                        Sakan.U.setEmail(sc.nextLine());

                        logger.info("Enter password: ");
                        Sakan.U.setPassword(sc.nextLine());
                        Login(usertype,Sakan.U.getEmail(),Sakan.U.getPassword());
                    }
                }
            }






        } catch (SQLException e) {
            e.printStackTrace();

        }



    }



}
