package main;

import java.sql.*;
import java.util.Scanner;

import static main.Sakan.*;
import static main.funcAddBuilding.addbuildingfunc;
import static main.funcAddFloor.addfloor;
import static main.funcAdmin.adminfunc;
import static main.funcOwner.ownerfunc;
import static main.funcRequestAction.requestAction;
import static main.funcSelectBuilding.invalidFlag;
import static main.funcSelectBuilding.selectbuilding;
import static main.funcSelectRequest.selectRequest;
import static main.funcSelectmyfloorfunc.selectmyfloor;
import static main.funcViewBuildingFunc.viewBuildingFunc;

public class funcCheckLogin {

    public static void checklogin(String email, String pass, String usertype){


        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        PreparedStatement tst= null;
        flagReflect=0;



        try {
            if(flagOwner==0){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT EMAIL,PASSWORD,USER_TYPE FROM USERS WHERE EMAIL = '" + email + "' AND PASSWORD = '" + pass + "'  AND user_type = '" + usertype + "'");
            rs = pst.executeQuery();


            if (rs.next()) {

                String tempE = rs.getString(1);
                String tempP = rs.getString(2);
                String tempU = rs.getString(3);
                if (!tempE.equals(null) && !tempP.equals(null) && !tempU.equals(null)) {
                    logger.info("Logged in successfully");

                    Sakan.flag1 = 1;
                }

            } else if (!rs.next()) {
                Scanner sc = new Scanner(System.in);
                logger.info("Invalid username or email");


                flaglogin = 1;
                tenantfunc(usertype);


            }
        }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT USER_ID FROM USERS WHERE EMAIL = '" + email + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                U.setUsersID(rs.getInt(1));

                if(usertype.equalsIgnoreCase("OWNERS")) {
                    Sakan.OnlineUser = U.getEmail();
                    while (true){

                        flagReflect=0;
                        Scanner sc = new Scanner(System.in);

                    String temp;


                    logger.info("(A) View my Buildings     ");
                    logger.info("(B) select building by ID ");
                    logger.info("(C) Add Building          ");
                    logger.info("(E) Main Menu(Logout)     ");


                    String ownsc = sc.nextLine();


                    ownerfunc("OWNERS", U.getUsersID(), ownsc,1);

                    if(ownsc.equalsIgnoreCase("B")){

                        logger.info("Enter the Building ID: ");
                        Sakan.B.setBuildingId(sc.nextInt());
                        selectbuilding( Sakan.B.getBuildingId(),U.getUsersID());

                        if(invalidFlag==1){
                            logger.info("Enter the Building ID: ");
                            Sakan.B.setBuildingId(sc.nextInt());

                            selectbuilding(Sakan.B.getBuildingId(),U.getUsersID());
                        }

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

                                viewBuildingFunc(Sakan.B.getOwnerId(), Sakan.B.getBuildingId(), view1,1);
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
                                else if (view1.equalsIgnoreCase("D")) {
                                    break;
                                }
                            }

                        }

                    }
                    else if (ownsc.equalsIgnoreCase("C")){
                        Scanner sf=new Scanner(System.in);

                        logger.info("(1) Back                                           ");


                        logger.info(" Enter Building_name: ");

                        Sakan.B.setBuildingName(sf.nextLine());



                        if(Sakan.B.getBuildingName().equalsIgnoreCase("1")){
                               flagOwner =1;
                               checklogin(email,pass,usertype);
                          //  ownerfunc("OWNERS",U.getUsersID(),ownsc);
                        }







                        logger.info(" Enter Location: ");
                        Sakan.B.setLocation(sf.nextLine());

                        if(Sakan.B.getLocation().equalsIgnoreCase("1")){
                            flagOwner =1;
                            checklogin(email,pass,usertype);
                            //  ownerfunc("OWNERS",U.getUsersID(),ownsc);
                        }
                        addbuildingfunc(U.getUsersID(),1);
                    }

                }
                }

                else if(usertype.equalsIgnoreCase("ADMIN")) {

                    Sakan.OnlineUser=U.getEmail();


                    Scanner sc = new Scanner(System.in);
                    while(true) {


                        logger.info("(A) View Requests                                  ");
                        logger.info("(B) Select request by ID                           ");
                        logger.info("(C) View System Observations                       ");
                        logger.info("(D) to delete all observations                     ");
                        logger.info("(E) Main menu (Log out)                            ");



                        String adminsc = sc.next();

                        adminfunc("ADMIN", U.getUsersID(), adminsc,1);

                        if(Sakan.flagAdminFunc ==1){

                            System.out.print("Enter the request ID: ");

                            Sakan.ar.setReqId(sc.nextInt());


                            selectRequest(Sakan.ar.getReqId(), 1);
                            while (flagSelectRequest == 1) {
                                Scanner sf = new Scanner(System.in);
                                logger.info("Do you want to accept this request or deleter it? ");
                                logger.info("(A)Accept    (B)delete    (C)Go back   ");
                                String answer = sf.next();
                                if (!answer.equalsIgnoreCase("A") && !answer.equalsIgnoreCase("B") && !answer.equalsIgnoreCase("C"))
                                    continue;
                                requestAction(Sakan.ar.getReqId(), answer, 1);
                                break;
                            }


                        }

                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }


//

    }

}
