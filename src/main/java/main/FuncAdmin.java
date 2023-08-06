package main;

import java.sql.SQLException;

import static main.Sakan.*;
import static main.FuncDeleteObservation.deleteObservations;
import static main.FuncViewObservation.viewObservations;
import static main.FuncViewRequest.viewRequests;
@SuppressWarnings("java:S1118")
public class FuncAdmin {

  public static int adminflag=0;
    public static void adminfunc(AdminPara adminPara) throws SQLException {
        adminflag=0;

        Sakan.flagAdminFunc =0;

       while(true){
        if (adminPara.adminsc().equalsIgnoreCase("A")) {

          adminflag=1;
            viewRequests();
            break;


        } else if (adminPara.adminsc().equalsIgnoreCase("B")) {
            adminflag=1;
            Sakan.flagAdminFunc =1;
            break;

        } else if (adminPara.adminsc().equalsIgnoreCase("D")) {

            adminflag=1;
            deleteObservations(adminPara.testp());
            break;




        } else if (adminPara.adminsc().equalsIgnoreCase("C")) {
            adminflag=1;
            viewObservations();
            break;

        } else if (adminPara.adminsc().equalsIgnoreCase("E")) {
            if(adminPara.testp() ==2) {
                adminflag = 1;
              break;
            }
            mainfunc();
            break;
        }
        adminflag=1;
           logger.info("Invalid input");
        break;

    }





    }



}
