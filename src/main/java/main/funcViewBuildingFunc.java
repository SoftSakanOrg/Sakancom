package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcAddFloor.addfloor;
import static main.funcOwner.ownerfunc;
import static main.funcSelectmyfloorfunc.selectmyfloor;

public class funcViewBuildingFunc {

    public static void viewBuildingFunc(int owner_ID,int building_ID) throws SQLException {
        Scanner st = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String view1;
        while (true) {

            logger.info("(A) View Floors                                    ");
            logger.info("(B) Select Floor by ID                             ");
            logger.info("(C) Add Floor                                      ");
            logger.info("(D) Back                                           ");
            logger.info("(E) Main menu (Log out)                            ");


            view1 = sc.nextLine();

            if (view1.equalsIgnoreCase("A")) {

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("SELECT * FROM floors WHERE  building_id = " + building_ID + "  ");
                    rs = pst.executeQuery();
                    if( !rs.next()){
                        logger.info("No floors available currently");
                    }
                    rs = pst.executeQuery();


                    while (rs.next()) {

                        String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t availability: "+ rs.getString(3)+ "\t|\t Price: "+ rs.getInt(4)+ "\t|\t Services: "+ rs.getString(5)+ "\t|\t Participants: "+ rs.getInt(6)+ "\t|\t Max_Participants: "+ rs.getInt(7)+ "\t|\t BedroomsNum: "+ rs.getInt(8)+ "\t|\t BathroomsNum: "+ rs.getInt(9)+ "\t|\t Balcony: "+ rs.getInt(10)+ "\t|\t Status: "+ rs.getString(11);
                        logger.info(content);


                    }


                } catch (SQLException e) {
                    e.printStackTrace();

                }


            }    else if (view1.equalsIgnoreCase("B")) {
                Scanner sc1 =new Scanner(System.in);
                logger.info("Enter the floor ID: ");

                Sakan.H.setHouseId(sc1.nextInt());

                selectmyfloor(building_ID,Sakan.H.getHouseId());

                if(flagSelectMyFloor==1){
                    while(true) {
                        Scanner sf1=new Scanner(System.in);
                        logger.info("Do you want to add  pictures for this FLOOR ?");
                        logger.info("A) yes . B) No");
                        String ans = sf1.next();

                        if (ans.equalsIgnoreCase("A")) {
                           logger.info(" Enter pictures: ");

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
                            logger.info("Pictures have been added");
                            break;
                        } else if (ans.equalsIgnoreCase("B")) {
                            break;
                        }
                    }
                }
            }

            else if (view1.equalsIgnoreCase("C")) {
               while(true) {
                   Scanner sf=new Scanner(System.in);
                   logger.info("(111) Back                                         ");


                   logger.info(" Enter Price: ");

                   Sakan.H.setHousePrice(sf.nextInt());

                   if (Sakan.H.getHousePrice() == 111) {

                       break;
                   }


                   logger.info(" Enter Services: ");
                   Sakan.H.setHouseServices(sf.next());

                   if (Sakan.H.getHouseServices().equalsIgnoreCase("111")) {

                       break;
                   }

                   logger.info(" How Many Participants Can Live In this Floor? ");
                   Sakan.H.setHouseMaxParticipants(sf.nextInt());

                   if (Sakan.H.getHouseMaxParticipants() == 111) {

                       break;
                   }

                   logger.info(" Enter the number of Bedrooms: ");
                   Sakan.H.setBedrooms(sf.nextInt());

                   if (Sakan.H.getBedrooms() == 111) {

                       break;
                   }


                   logger.info(" Enter the number of Bathrooms: ");
                   Sakan.H.setBathrooms(sf.nextInt());

                   if (Sakan.H.getBathrooms() == 111) {

                       break;
                   }


                   logger.info(" how many Does Balconies does it have? ");
                   Sakan.H.setContBalcony(sf.nextInt());

                   if (Sakan.H.getContBalcony() == 111) {

                       break;
                   }


                   addfloor(building_ID);
                   break;
               }
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
