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


    public static void adminfunc(String usertype, int admin_ID, String adminsc) throws SQLException {
        Sakan.flagAdminFunc =0;
       while(true){
        if (adminsc.equalsIgnoreCase("A")) {


            viewRequests();
            break;


        } else if (adminsc.equalsIgnoreCase("B")) {

            Sakan.flagAdminFunc =1;
            break;

        } else if (adminsc.equalsIgnoreCase("C")) {


            viewObservations();
            break;


        } else if (adminsc.equalsIgnoreCase("D")) {


            deleteObservations(1);
            break;

        } else if (adminsc.equalsIgnoreCase("E")) {
            Mainfunc();
            break;
        }
        System.out.println("Invalid input");
        break;

    }





    }



}
