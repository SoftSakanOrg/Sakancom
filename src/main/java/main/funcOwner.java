package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcAddBuilding.addbuildingfunc;
import static main.funcAddFloor.addfloor;
import static main.funcSelectBuilding.selectbuilding;
import static main.funcSelectmyfloorfunc.selectmyfloor;
import static main.funcViewBuilding.viewbuilding;
import static main.funcViewBuildingFunc.viewBuildingFunc;

public class funcOwner {

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
                    Mainfunc();
                }

            }
           // else logger.info("Invalid input");


    }


}
