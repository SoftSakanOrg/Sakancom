package main;

import java.sql.*;

import static main.Sakan.*;
import static main.FuncViewBuilding.viewbuilding;

public class FuncOwner {

    public static int ownerFlag;
    public static void ownerfunc(OwnerFuncParam ownerFuncParam) throws SQLException {

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


            if (ownerFuncParam.ownsc().equalsIgnoreCase("A")) {

                ownerFlag=1;

                if(ownerFuncParam.testp() ==1) {

                    viewbuilding(ownerFuncParam.ownerID(), 1);
                }

            }   else if (ownerFuncParam.ownsc().equalsIgnoreCase("E")) {

                ownerFlag=1;

                if(ownerFuncParam.testp() ==1) {
                    mainfunc();
                }

            }



    }


}
