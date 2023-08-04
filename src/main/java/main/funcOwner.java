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

    public static void ownerfunc(String usertype,int ownerID,String ownsc) throws SQLException {

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


            if (ownsc.equalsIgnoreCase("A")) {


                viewbuilding(ownerID,1);


            }   else if (ownsc.equalsIgnoreCase("E")) {


                Mainfunc();


            }
           // else logger.info("Invalid input");


    }


}
