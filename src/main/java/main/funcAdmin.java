package main;

import java.sql.SQLException;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcViewObservation.viewObservations;
import static main.funcViewRequest.viewRequests;

public class funcAdmin {


    public static void adminfunc(String usertype, int admin_ID) throws SQLException {

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(A) View Requests                                  ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(B) Select request by ID                           ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(C) View System Observations                       ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(D) to delete all observations                     ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(E) Main menu (Log out)                            ██");
            System.out.println("███████████████████████████████████████████████████████");


            String adminsc = sc.nextLine();

            if (adminsc.equalsIgnoreCase("A")) {


                viewRequests();


            }
            else if (adminsc.equalsIgnoreCase("B")) {


                selectRequest();


            }
            else if (adminsc.equalsIgnoreCase("C")) {


                viewObservations();


            }
            else if (adminsc.equalsIgnoreCase("D")) {


                deleteObservations();


            }

            else if (adminsc.equalsIgnoreCase("E")) {
                Mainfunc();

            }


        }





    }



}
