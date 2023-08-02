package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcAdmin.adminfunc;
import static main.funcOwner.ownerfunc;
import static main.funcRequestAction.requestAction;
import static main.funcSelectRequest.selectRequest;

public class funcCheckLogin {

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
                    logger.info("Logged in successfully");

                    Sakan.flag1 = 1;
                }

            } else if (!rs.next()) {
                Scanner sc=new Scanner(System.in);
                logger.info("Invalid username or email");


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


                    Scanner sc = new Scanner(System.in);
                    while(true) {


                        logger.info("(A) View Requests                                  ");
                        logger.info("(B) Select request by ID                           ");
                        logger.info("(C) View System Observations                       ");
                        logger.info("(D) to delete all observations                     ");
                        logger.info("(E) Main menu (Log out)                            ");



                        String adminsc = sc.next();

                        adminfunc("ADMIN", U.getUsersID(), adminsc,1);

                        if(Sakan.flagAdminFunc ==1){

                            System.out.print("Enter the request ID: ");

                            Sakan.ar.setReqId(sc.nextInt());


                            selectRequest(Sakan.ar.getReqId(), 1);
                            while (flagSelectRequest == 1) {
                                Scanner sf = new Scanner(System.in);
                                logger.info("Do you want to accept this request or deleter it? ");
                                logger.info("(A)Accept    (B)delete    (C)Go back   ");
                                String answer = sf.next();
                                if (!answer.equalsIgnoreCase("A") && !answer.equalsIgnoreCase("B") && !answer.equalsIgnoreCase("C"))
                                    continue;
                                requestAction(Sakan.ar.getReqId(), answer, 1);
                                break;
                            }


                        }

                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }


//

    }

}
