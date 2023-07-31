package main;

import java.util.Scanner;

import static main.Sakan.*;
import static main.funcSelectBuilding.selectbuilding;
import static main.funcViewBuilding.viewbuilding;

public class funcOwner {

    public static void ownerfunc(String usertype,int ownerID){


        while(true) {
            Scanner sc = new Scanner(System.in);

            String temp;

            System.out.println("██████████████████████████████");
            System.out.println("██(A) View my Buildings     ██");
            System.out.println("██████████████████████████████");
            System.out.println("██(B) select building by ID ██");
            System.out.println("██████████████████████████████");
            System.out.println("██(C) Add Building          ██");
            System.out.println("██████████████████████████████");
            System.out.println("██(E) Main Menu(Logout)     ██");
            System.out.println("██████████████████████████████");

            String ownsc = sc.nextLine();

            if (ownsc.equalsIgnoreCase("A")) {


                viewbuilding(ownerID);


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
