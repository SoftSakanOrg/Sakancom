package main;

import java.sql.*;

import static main.Sakan.*;
import static main.FuncViewBuilding.viewbuilding;
@SuppressWarnings("java:S1118")
public class FuncOwner {

    public static int ownerflag;
    public static void ownerfunc(OwnerFuncParam ownerFuncParam) throws SQLException {


            if (ownerFuncParam.ownsc().equalsIgnoreCase("A")) {

                ownerflag =1;

                if(ownerFuncParam.testp() ==1) {

                    viewbuilding(ownerFuncParam.ownerID(), 1);
                }

            }   else if (ownerFuncParam.ownsc().equalsIgnoreCase("E")) {

                ownerflag =1;

                if(ownerFuncParam.testp() ==1) {
                    mainfunc();
                }

            }



    }


}
