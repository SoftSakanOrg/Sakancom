package main;

import java.sql.SQLException;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcDeleteObservation.deleteObservations;
import static main.funcRequestAction.requestAction;
import static main.funcSelectRequest.selectRequest;
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


            String adminsc = sc.next();

            if (adminsc.equalsIgnoreCase("A")) {


                viewRequests();


            }
            else if (adminsc.equalsIgnoreCase("B")) {

                System.out.print("Enter the request ID: ");

                Sakan.ar.setReqId(sc.nextInt());


                selectRequest(Sakan.ar.getReqId(),1);
               while(flagSelectRequest==1) {
                   Scanner sf = new Scanner(System.in);
                   System.out.println("Do you want to accept this request or deleter it? ");
                   System.out.println("(A)Accept    (B)delete    (C)Go back   ");
                   String answer = sf.next();
                   if(!answer.equalsIgnoreCase("A") && !answer.equalsIgnoreCase("B") && !answer.equalsIgnoreCase("C")) continue;
                   requestAction(Sakan.ar.getReqId(), answer, 1);
                   break;
               }

            }
            else if (adminsc.equalsIgnoreCase("C")) {


                viewObservations();


            }
            else if (adminsc.equalsIgnoreCase("D")) {


                deleteObservations(1);


            }

            else if (adminsc.equalsIgnoreCase("E")) {
                Mainfunc();

            }


        }





    }



}
