package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.Signup;
import static main.Sakan.flag11;
import static main.usersLoginFunc.Login;

public class checkEmailFunc {


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
                            System.out.println("This email already exists..");
                            Signup(usertype);
                        }
                    }


                }
            }
            else if(!rs.next()){
                if (flag11==0) {
                    if (func == 2) {
                        System.out.println("a user with that email doesn't exists..");

                        Scanner sc=new Scanner(System.in);
                        System.out.println("███████████████████████████████");
                        System.out.println("██(1) To go back to main menu██");
                        System.out.println("███████████████████████████████");

                        System.out.print("Enter your email: ");
                        Sakan.U.setEmail(sc.nextLine());

                        System.out.print("Enter password: ");
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
