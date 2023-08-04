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

    public static void ownerfunc(String usertype,int ownerID) throws SQLException {

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


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

                    System.out.print("Enter the Building ID: ");
                    Sakan.B.setBuildingId(sc.nextInt());

                    selectbuilding(ownerID);
                    while(true){
                    if (flagSelectBuilding == 1) {
                        String view1;
                        while (true) {


                            logger.info("(A) View Floors                                    ");
                            logger.info("(B) Select Floor by ID                             ");
                            logger.info("(C) Add Floor                                      ");
                            logger.info("(D) Back                                           ");
                            logger.info("(E) Main menu (Log out)                            ");


                            view1 = sc.next();
                            if (view1.equalsIgnoreCase("A") || view1.equalsIgnoreCase("B") || view1.equalsIgnoreCase("C") || view1.equalsIgnoreCase("D") || view1.equalsIgnoreCase("E")) {
                                break;
                            }
                            logger.info("Invalid input");
                        }

                        viewBuildingFunc(Sakan.B.getOwnerId(), Sakan.B.getBuildingId(), view1);
                        if (view1.equalsIgnoreCase("B")) {
                            Scanner sc1 = new Scanner(System.in);
                            logger.info("Enter the floor ID: ");

                            Sakan.H.setHouseId(sc1.nextInt());

                            selectmyfloor(Sakan.B.getBuildingId(), Sakan.H.getHouseId());

                            if (flagSelectMyFloor == 1) {
                                while (true) {
                                    Scanner sf1 = new Scanner(System.in);
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
                        } else if (view1.equalsIgnoreCase("C")) {
                            while (true) {
                                Scanner sf = new Scanner(System.in);
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


                                addfloor(Sakan.B.getBuildingId(),1);
                                break;
                            }
                        }
                    }
                }

            } else if (ownsc.equalsIgnoreCase("C")) {


                addbuildingfunc(ownerID);


            } else if (ownsc.equalsIgnoreCase("E")) {


                Mainfunc();


            }
            else System.out.print("Invalid input");
        }

    }


}
