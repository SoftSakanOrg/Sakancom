package main;

import java.sql.*;

import static main.Sakan.*;
import static main.FuncViewBuilding.viewbuilding;

public class FuncOwner {

    public static int ownerFlag;
    public static void ownerfunc(String usertype,int ownerID,String ownsc,int testp) throws SQLException {

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


            if (ownsc.equalsIgnoreCase("A")) {

                ownerFlag=1;

                if(testp==1) {

                    viewbuilding(ownerID, 1);
                }

            }   else if (ownsc.equalsIgnoreCase("E")) {

                ownerFlag=1;

                if(testp==1) {
                    mainfunc();
                }

            }



    }


}
