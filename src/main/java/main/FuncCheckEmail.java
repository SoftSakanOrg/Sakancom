package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.FuncUsersLogin.Login;
@SuppressWarnings("java:S1118")
public class FuncCheckEmail {


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

                extracted(func, usertype);
            }
            else if(!rs.next() && flag11==0 && func == 2){


                        logger.info("a user with that email doesn't exists..");

                        Scanner sc=new Scanner(System.in);

                        logger.info("(1) To go back to main menu");

                        logger.info("Enter your email: ");
                        Sakan.u.setEmail(sc.nextLine());

                        logger.info("Enter password: ");
                        Sakan.u.setPassword(sc.nextLine());
                        Login(usertype,Sakan.u.getEmail(),Sakan.u.getPassword());


            }






        } catch (SQLException e) {
            e.printStackTrace();

        }



    }

    private static void extracted(int func, String usertype) {
        if(Sakan.flag1==0 && func == 1 ) {


                    logger.info("This email already exists..");
                    signup(usertype);




        }
    }


}
