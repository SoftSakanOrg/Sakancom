package main;

import java.sql.*;
import java.util.Scanner;


import static main.Sakan.flagSelectMyFloor;
import static main.Sakan.logger;
import static main.funcAddFloor.addfloor;
import static main.funcSelectmyfloorfunc.selectmyfloor;
import static main.funcViewBuildingFunc.viewBuildingFunc;

public class funcSelectBuilding {

    public static void selectbuilding(int owner_id){
        Scanner sc=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        System.out.print("Enter the Building ID: ");
        Sakan.B.setBuildingId(sc.nextInt());
        while (true) {


            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst = connection.prepareStatement("SELECT * FROM building WHERE building_id = '" + Sakan.B.getBuildingId() + "' AND  OWNER_ID = '" + owner_id + "'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    Sakan.B.setBuildingName(rs.getString(3));
                    logger.info("\t\t\t          ★★★★★★★★   " + Sakan.B.getBuildingName() + "  ★★★★★★★★            \t\t\t");
                    String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Location: " + rs.getString(4) + "\t|\t Floors_num: " + rs.getInt(5) + "\t|\t Owner_name: " + rs.getString(6) + "\t|\t Contact_num: " + rs.getInt(7);
                    logger.info(content);
                    Sakan.ar.setBuildingName(Sakan.B.getBuildingName());
                    Sakan.ar.setOwnerName(rs.getString(6));
                    Sakan.ar.setContactNumber(rs.getInt(7));
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

                    viewBuildingFunc(rs.getInt(2), rs.getInt(1), view1);
                    if (view1.equalsIgnoreCase("B")){
                        Scanner sc1 = new Scanner(System.in);
                    logger.info("Enter the floor ID: ");

                    Sakan.H.setHouseId(sc1.nextInt());

                    selectmyfloor(rs.getInt(1), Sakan.H.getHouseId());

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
                }
                    else if(view1.equalsIgnoreCase("C")){
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


                            addfloor(rs.getInt(1),1);
                            break;
                        }
                    }
                } else if (!rs.next()) {

                    logger.info("Invalid building ID...");
                    break;

                }


            } catch (SQLException e) {
                e.printStackTrace();

            }

        }

    }






}
