package main;

import java.sql.SQLException;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcAddBuilding.addbuildingfunc;
import static main.funcSelectBuilding.selectbuilding;
import static main.funcViewBuilding.viewbuilding;

public class funcOwner {

    public static void ownerfunc(String usertype,int ownerID) throws SQLException {


        while(true) {
            Scanner sc = new Scanner(System.in);

            String temp;


            logger.info("(A) View my Buildings     ");
            logger.info("(B) select building by ID ");
            logger.info("(C) Add Building          ");
            logger.info("(E) Main Menu(Logout)     ");


            String ownsc = sc.nextLine();

            if (ownsc.equalsIgnoreCase("A")) {


                viewbuilding(ownerID,1);


            } else if (ownsc.equalsIgnoreCase("B")) {


                selectbuilding(ownerID);


            } else if (ownsc.equalsIgnoreCase("C")) {


                addbuildingfunc(ownerID);


            } else if (ownsc.equalsIgnoreCase("E")) {


                Mainfunc();


            }
            else System.out.print("Invalid input");
        }

    }


}
