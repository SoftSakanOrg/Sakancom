package main;

import java.sql.SQLException;

import static main.Sakan.*;
import static main.FuncDeleteObservation.deleteObservations;
import static main.FuncViewObservation.viewObservations;
import static main.FuncViewRequest.viewRequests;
@SuppressWarnings("java:S1118")
public class FuncAdmin {

  private static int adminflag=0;

    public FuncAdmin() {

        /***
         do nothing
         ***/
    }

    public static int getAdminflag() {
        return adminflag;
    }

    public static void setAdminflag(int adminflag) {
        FuncAdmin.adminflag = adminflag;
    }

    public static void adminfunc(AdminPara adminPara) throws SQLException {
        adminflag=0;

        Sakan.flagAdminFunc =0;

       while(true){
        if (adminPara.adminsc().equalsIgnoreCase("A")) {

          setAdminflag(1);
            viewRequests();
            break;


        } else if (adminPara.adminsc().equalsIgnoreCase("B")) {
            setAdminflag(1);
            Sakan.flagAdminFunc =1;
            break;

        } else if (adminPara.adminsc().equalsIgnoreCase("D")) {

            setAdminflag(1);
            deleteObservations(adminPara.testp());
            break;




        } else if (adminPara.adminsc().equalsIgnoreCase("C")) {
            setAdminflag(1);
            viewObservations();
            break;

        } else if (adminPara.adminsc().equalsIgnoreCase("E")) {
            if(adminPara.testp() ==2) {
                setAdminflag(1);
              break;
            }
            mainfunc();
            break;
        }
           setAdminflag(1);
           logger.info("Invalid input");
        break;

    }





    }



}
