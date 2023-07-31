package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcAddFloor.addfloor;
import static main.funcOwner.ownerfunc;
import static main.funcSelectmyfloorfunc.selectmyfloor;

public class funcViewBuildingFunc {

    public static void viewBuildingFunc(int owner_ID,int building_ID) {
        Scanner st = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String view1;
        while (true) {
            System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(A) View Floors                                    ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(B) Select Floor by ID                             ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(C) Add Floor                                      ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(D) Back                                           ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(E) Main menu (Log out)                            ██");
            System.out.println("███████████████████████████████████████████████████████");

            view1 = sc.nextLine();

            if (view1.equalsIgnoreCase("A")) {

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("SELECT * FROM floors WHERE  building_id = " + building_ID + "  ");
                    rs = pst.executeQuery();
                    if( !rs.next()){
                        System.out.println("No floors available currently");
                    }
                    rs = pst.executeQuery();


                    while (rs.next()) {

                        String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t availability: "+ rs.getString(3)+ "\t|\t Price: "+ rs.getInt(4)+ "\t|\t Services: "+ rs.getString(5)+ "\t|\t Participants: "+ rs.getInt(6)+ "\t|\t Max_Participants: "+ rs.getInt(7)+ "\t|\t BedroomsNum: "+ rs.getInt(8)+ "\t|\t BathroomsNum: "+ rs.getInt(9)+ "\t|\t Balcony: "+ rs.getInt(10)+ "\t|\t Status: "+ rs.getString(11);
                        System.out.println(content);


                    }


                } catch (SQLException e) {
                    e.printStackTrace();

                }


            }    else if (view1.equalsIgnoreCase("B")) {
                Scanner sc1 =new Scanner(System.in);
                System.out.print("Enter the floor ID: ");

                Sakan.H.setHouseId(sc1.nextInt());

                selectmyfloor(building_ID);

                if(flagSelectMyFloor==1){
                    while(true) {
                        Scanner sf1=new Scanner(System.in);
                        System.out.println("Do you want to add  pictures for this FLOOR ?");
                        System.out.println("A) yes . B) No");
                        String ans = sf1.next();

                        if (ans.equalsIgnoreCase("A")) {
                            System.out.println(" Enter pictures: ");

                            Sakan.hpc.setHousePicture(sf1.next());

                            try {
                                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                                pst = connection.prepareStatement("INSERT INTO HOUSE_PIC(FLOOR_ID,PICTURE) VALUES (?,?)");
                                pst.setInt(1, Sakan.H.getHouseId());
                                pst.setString(2, Sakan.hpc.getHousePicture());
                                pst.executeUpdate();

                                pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                pst.setString(1, Sakan.OnlineUser + " has added a picture (" + Sakan.hpc.getHousePicture() + ") to his apartment ");

                                pst.executeUpdate();


                            } catch (SQLException e) {
                                e.printStackTrace();

                            }
                            System.out.println("Pictures have been added");
                            break;
                        } else if (ans.equalsIgnoreCase("B")) {
                            break;
                        }
                    }
                }
            }

            else if (view1.equalsIgnoreCase("C")) {

                addfloor(building_ID);

            }

            else if(view1.equalsIgnoreCase("D")){
                ownerfunc("OWNERS",owner_ID);
            }
            else if(view1.equalsIgnoreCase("E")){
                Mainfunc();
            }
        }
    }



}
