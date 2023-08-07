package main;

import java.sql.*;

import static main.Sakan.*;
import static main.FuncViewBuilding.viewbuilding;
@SuppressWarnings("java:S1118")
public class FuncOwner {

    private static int ownerflag;

    public static int getOwnerflag() {
        return ownerflag;
    }

    public static void setOwnerflag(int ownerflag) {
        FuncOwner.ownerflag = ownerflag;
    }

    public static void ownerfunc(OwnerFuncParam ownerFuncParam) throws SQLException {


            if (ownerFuncParam.ownsc().equalsIgnoreCase("A")) {

                setOwnerflag(1);

                if(ownerFuncParam.testp() ==1) {

                    viewbuilding(ownerFuncParam.ownerID());
                }

            }   else if (ownerFuncParam.ownsc().equalsIgnoreCase("E")) {

                setOwnerflag(1);

                if(ownerFuncParam.testp() ==1) {
                    mainfunc();
                }

            }



    }


}
