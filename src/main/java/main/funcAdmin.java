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

  public static int adminflag=0;
    public static void adminfunc(String usertype, int admin_ID, String adminsc,int testp) throws SQLException {
        adminflag=0;

        Sakan.flagAdminFunc =0;

       while(true){
        if (adminsc.equalsIgnoreCase("A")) {

          adminflag=1;
            viewRequests();
            break;


        } else if (adminsc.equalsIgnoreCase("B")) {
            adminflag=1;
            Sakan.flagAdminFunc =1;
            break;

        } else if (adminsc.equalsIgnoreCase("D")) {

            adminflag=1;
            deleteObservations(testp);
            break;




        } else if (adminsc.equalsIgnoreCase("C")) {
            adminflag=1;
            viewObservations();
            break;

        } else if (adminsc.equalsIgnoreCase("E")) {
            if(testp==2) {
                adminflag = 1;
              break;
            }
            Mainfunc();
            break;
        }
        adminflag=1;
           logger.info("Invalid input");
        break;

    }





    }



}
