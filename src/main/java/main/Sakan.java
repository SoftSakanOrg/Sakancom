package main;//import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.System.exit;
import static java.lang.System.getLogger;
import static main.funcCheckEmail.checkemail;
import static main.funcCheckLogin.checklogin;
import static main.funcUsersLogin.Login;
import java.util.logging.Logger;

public class Sakan {

    static String url = "jdbc:mysql://localhost:3306/Sakan";

    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static String s = "\t|\t ID: ";
    static String s1 = "\t|\t";
     static  String tenants = "TENANTS";
    static  String owners = "OWNERS";
    static  String admin = "ADMIN";
    static int flag1 = 0;

     static int flag11 = 0;
    static int flag2 = 0;

    static int whileflag = 0;
    static int whileflag2 = 0;


     static  int flaglogin = 0;

     static  int flagRequestAction = 0;

     static  int flagSelectRequest = 0;

     static  int flagAdminFunc = 0;

    static  int flagSelectMyFloor = 0;
    static  int flagSelectBuilding = 0;

     static  int flagOwner = 0;

    static  int flagReflect = 0;




     static String onlineUser = "" ;

     static Users u =new Users();




    static Floors h =new Floors();
    static Furniture f =new Furniture();

    static houseParticipants hp=new houseParticipants();
    static housePictures hpc=new housePictures();
    static furniturePictures fpc=new furniturePictures();

    static Buildings b =new Buildings();

    static advertismentRequests ar = new advertismentRequests();


    static bookingInfo bi=new bookingInfo();



    static Logger logger =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void mainfunc() throws SQLException {
           Sakan.flag1 =0;
           Sakan.flag2 = 0;
           Sakan.onlineUser = "";
           Sakan.flag11 = 0;
           Sakan.flaglogin=0;
           Sakan.flagOwner = 0;


        Scanner sc=new Scanner(System.in);

        String c ;

        while (true)
        {


            logger.info( "(1) Tenant " );
            logger.info( "(2) Owner  " );
            logger.info( "(3) Admin  " );

            logger.info( "Choose between the specific users :  " );
            c= sc.nextLine();



            if(c.equalsIgnoreCase("1")){
                tenantfunc(tenants);


            }
            else if(c.equalsIgnoreCase("2")){
                tenantfunc(owners);


            }

            else if(c.equalsIgnoreCase("3")){
                tenantfunc(admin);


            }
            else if(c.equalsIgnoreCase("exit")){
                exit(0);
            }
            else{
                logger.info("Please make sure to enter the right user........");


            }


        }
    }

    public static void tenantfunc(String usertype) throws SQLException {
        Scanner sc=new Scanner(System.in);
        String temp;

          if(Sakan.flaglogin==1)temp="2";
       else temp="3";





        if(Sakan.flag2==0){
            if(Sakan.flaglogin==0) {


                if (!usertype.equalsIgnoreCase(admin)) {

                    logger.info("(1) Sign up          ");}
                    logger.info("(2) Log in           ");
                    logger.info("(3) Back to main menu");

            }

      if(Sakan.flaglogin==0)
        temp=sc.nextLine();

        if(temp.equalsIgnoreCase("3")){
            mainfunc();

        }

        else if(temp.equalsIgnoreCase("1") && !usertype.equalsIgnoreCase(admin)) {
            Signup(usertype);


        }

        else if(temp.equalsIgnoreCase("2")) {



            logger.info("(1) To go back to main menu");

            logger.info("Enter your email: ");
            Sakan.u.setEmail(sc.nextLine());

            logger.info("Enter password: ");
            Sakan.u.setPassword(sc.nextLine());


            Login(usertype,Sakan.u.getEmail(),Sakan.u.getPassword());


        }
       }
        Scanner sv = new Scanner(System.in);
        String view;

         if(usertype.equalsIgnoreCase(tenants)){
       while(true) {

           tenantId();

           logger.info("\nHere is a menu showing the available options:-");
           logger.info("(A) View available Apartments                     ");
           logger.info("(B) To Select one of the available floors using ID");
           logger.info("(C) View furnitures for sale                       ");
           logger.info("(E) To Select a furniture to buy using ID          ");
           logger.info("(F) Advertise a furniture for sale                 ");
           logger.info("(G) View personal info of booking                  ");
           logger.info("(H) Main menu (Log out)                            ");

           view = sv.nextLine();
           if (view.equalsIgnoreCase("A")) {

             viewfloor();

           } else if (view.equalsIgnoreCase("B")) {
               selectfloor();
           } else if (view.equalsIgnoreCase("C")) {

               viewfurniture();

           }else if(view.equalsIgnoreCase("E")){
            selectfurniture();
           } else if(view.equalsIgnoreCase("F")){
              addfurniture(Sakan.onlineUser);

           }  else if(view.equalsIgnoreCase("G")){
               viewBookingInfo(Sakan.u.getUsersID());
           }

           else if (view.equalsIgnoreCase("H")) {
               mainfunc();

           }

       }
    }
    }



    public static void tenantId() throws SQLException {


        PreparedStatement pst= null;
        ResultSet rs = null;



            pst = connection.prepareStatement("SELECT user_id,USERNAME FROM USERS WHERE EMAIL='" + Sakan.onlineUser + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                Sakan.bi.setTenantId(rs.getInt(1));
                Sakan.u.setUsername(rs.getString(2));
                Sakan.u.setUsersID(rs.getInt(1));
            }

    }

public static void viewBookingInfo(int tenantId) throws SQLException {


    PreparedStatement pst= null;
    ResultSet rs = null;




        pst = connection.prepareStatement("SELECT * FROM BOOKING_INFO WHERE TENANT_ID = '" +tenantId+"'");
        rs = pst.executeQuery();
        if(!rs.next()){
            logger.info("You don`t have any rented apartment yet...");
        }
        rs= pst.executeQuery();
        while (rs.next()) {

            String content = "\t|\t Tenant_Name: " + rs.getString(2) + "\t|\t Owner_Name: "+ rs.getString(3)+ "\t|\t Contact_info: "+ rs.getInt(4)+ "\t|\t Rent_Date: "+ rs.getString(5)+ "\t|\t ";
            logger.info(content);
        }






}



    public static void viewfloor() throws SQLException {

        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;



            pst = connection.prepareStatement("SELECT * FROM floors WHERE availability = 'available' AND Status = 'Advertised' ");
            tst = connection.prepareStatement("SELECT B.location,B.building_name FROM floors F,building B WHERE F.building_id = B.building_id ");
            rs = pst.executeQuery();
            ts = tst.executeQuery();
            while (rs.next()) {
                ts.next();

                String content = s + rs.getInt(1) + "\t|\t availability: "+ rs.getString(3)+ "\t|\t Price: "+ rs.getInt(4)+ "\t|\t Services: "+ rs.getString(5)+ "\t|\t Participants: "+ rs.getInt(6)+ "\t|\t Max_Participants: "+ rs.getInt(7)+ "\t|\t BedroomsNum: "+ rs.getInt(8)+ "\t|\t BathroomsNum: "+ rs.getInt(9)+ "\t|\t Balcony: "+ rs.getInt(10)+ "\t|\t Status: "+ rs.getString(11);
                logger.info(content);
            }




    }

    public static void selectfloor() throws SQLException {

        Scanner sc=new Scanner(System.in);
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;
        logger.info("Enter the floor ID: ");

        Sakan.h.setHouseId(sc.nextInt());



            pst = connection.prepareStatement("SELECT * FROM floors WHERE floor_id = '" +  Sakan.h.getHouseId() + "' AND availability = 'available' AND Status = 'Advertised'"  );
            tst= connection.prepareStatement("SELECT building_id FROM floors WHERE floor_id = '" +  Sakan.h.getHouseId() + "' AND availability = 'available'  AND Status = 'Advertised'" );
            ts = tst.executeQuery();
            rs = pst.executeQuery();

            if(ts.next()){
                Sakan.b.setBuildingId(ts.getInt(1));
            }

            if (rs.next()) {

                String content = s + rs.getString(1)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + s1;
                logger.info(content);
                viewfloorsfunc(Sakan.h.getHouseId(),Sakan.b.getBuildingId(),Sakan.onlineUser);


            }
            else if(!rs.next()){
                logger.info("Please enter a valid floor ID...");

            }





    }

    public static void viewfurniture() throws SQLException {

        PreparedStatement pst= null;
        ResultSet rs = null;



            pst = connection.prepareStatement("SELECT * FROM furniture WHERE status = 'forsale' ");
            rs = pst.executeQuery();
            while (rs.next()) {

                String content = s+ rs.getInt(1) + " \t|\t price: " + rs.getInt(3) + "\t|\t description: " + rs.getString(4) + "\t|\t status: " + rs.getString(5) + s1;
                logger.info(content);
            }




    }

    public static void selectfurniture() throws SQLException {
        Scanner sc=new Scanner(System.in);

        PreparedStatement pst= null;
        ResultSet rs = null;
        logger.info("Enter the furniture ID: ");

       Sakan.f.setFurnitureID(sc.nextInt());



            pst = connection.prepareStatement("SELECT * FROM furniture WHERE furniture_id = '" + Sakan.f.getFurnitureID() + "' AND status = 'forsale'" );
            rs = pst.executeQuery();
            if (rs.next()) {
               Sakan.f.setFurnitureDescription(rs.getString(4));
                String content = "\t|\t ID: " + rs.getInt(1) + " \t|\t price: " + rs.getInt(3) + "\t|\t description: " + rs.getString(4) + s1;
                logger.info(content);
                viewfurnituresfunc(  Sakan.f.getFurnitureDescription(),Sakan.f.getFurnitureID());


            }
            else if(!rs.next()){
                logger.info("Please enter a valid furniture ID...");

            }







    }

    public static void  addfurniture(String Temail) throws SQLException {
        Scanner sf=new Scanner(System.in);

        PreparedStatement pst = null;
        ResultSet rs = null;
        Sakan.u.setUsersID(0);

        int furID=0;
        Scanner scon=new Scanner(System.in);
        int furIDVal=0;

       Sakan.f.setFurnitureStatus("forsale");




        logger.info("(1) Back                                           ");


        logger.info(" Enter description: ");

       Sakan.f.setFurnitureDescription(sf.nextLine());

       if(Sakan.f.getFurnitureDescription().equalsIgnoreCase("1")){
           Sakan.flag2 = 1;
           tenantfunc(tenants);
       }




        int price;

        logger.info(" Enter price: ");
         Sakan.f.setFurniturePrice(sf.nextInt());

        if(String.valueOf(Sakan.f.getFurniturePrice()).equalsIgnoreCase("1")){
            Sakan.flag2 = 1;
            tenantfunc(tenants);
        }





         pst = connection.prepareStatement("SELECT user_id FROM USERS WHERE EMAIL='" + Temail + "'");
         rs = pst.executeQuery();

         if (rs.next()) {
             Sakan.u.setUsersID(rs.getInt(1));
         }


         pst = connection.prepareStatement("INSERT INTO FURNITURE(user_ID,PRICE,DESCRIPTION,STATUS) VALUES (?,?,?,?)");

         pst.setInt(1,  Sakan.u.getUsersID());
         pst.setInt(2, Sakan.f.getFurniturePrice());
         pst.setString(3, Sakan.f.getFurnitureDescription());
         pst.setString(4, Sakan.f.getFurnitureStatus());

         pst.executeUpdate();

         pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

         pst.setString(1, Sakan.onlineUser + " has added a new furniture (" + Sakan.f.getFurnitureDescription() + "," + Sakan.f.getFurniturePrice() + "$)");

         pst.executeUpdate();

         logger.info("You have successfully added your furnitures...");





         pst = connection.prepareStatement("SELECT FURNITURE_ID FROM FURNITURE ORDER BY FURNITURE_ID DESC LIMIT 1");
         rs= pst.executeQuery();

         if(rs.next()){
          Sakan.f.setFurnitureID(rs.getInt(1));
         }




         pst = connection.prepareStatement("SELECT FURNITURE_ID FROM FURNITURE WHERE FURNITURE_ID='" + Sakan.f.getFurnitureID() + "'");
         rs = pst.executeQuery();
         if (rs.next()) {
             Sakan.f.setFurnitureID(rs.getInt(1));
         }

        logger.info("Do you want to add  pictures for this furniture?");
        logger.info("A) yes . B) No");
     String ans = scon.nextLine();

     if (ans.equalsIgnoreCase("A")) {
         logger.info(" Enter pictures: ");

         Sakan.fpc.setFurniturePicture(scon.nextLine());
         if ( Sakan.fpc.getFurniturePicture().equalsIgnoreCase("1")) {
             Sakan.flag2 = 1;
             tenantfunc(tenants);
         }



             pst = connection.prepareStatement("INSERT INTO FURNITURE_PIC(FURNITURE_ID,PICTURE) VALUES (?,?)");
             pst.setInt(1, Sakan.f.getFurnitureID());
             pst.setString(2, Sakan.fpc.getFurniturePicture());
             pst.executeUpdate();

             pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

             pst.setString(1, Sakan.onlineUser + " has added a new picture (" + Sakan.fpc.getFurniturePicture() + " to the furniture with ID: (" + Sakan.f.getFurnitureID()+ ")");

             pst.executeUpdate();

         logger.info("Pictures have been added");
     }


     Sakan.flag2 = 1;
     tenantfunc(tenants);

    }

    public static void Signup(String usertype) throws SQLException {


      if (Sakan.flag1 == 0) {
          Scanner sc = new Scanner(System.in);

        ;
          PreparedStatement pst = null;
          ResultSet rs = null;
          PreparedStatement tst= null;


          logger.info("(1) To go back to main menu");





          logger.info("Enter your email: ");


              Sakan.u.setEmail(sc.nextLine());

          if (Sakan.u.getEmail().equalsIgnoreCase("1")) {
              mainfunc();

          }
          if (!Sakan.u.getEmail().contains("@") || !u.getEmail().contains(".")) {
              logger.info("Please enter a valid email...");
              Signup(usertype);
          }



          checkemail(Sakan.u.getEmail(), 1, usertype);
              if(Sakan.flag1==0) {

                  logger.info("Enter username: ");
                  Sakan.u.setUsername(sc.nextLine());
                  if (Sakan.u.getUsername().equalsIgnoreCase("1")) {
                      mainfunc();

                  }



                  logger.info("Enter password: ");
                  Sakan.u.setPassword(sc.nextLine());

                  if (Sakan.u.getPassword().equalsIgnoreCase("1")) {
                      mainfunc();

                  }

                  logger.info("Enter Contact Number: ");
                      Sakan.u.setContactNum(sc.nextLine());

                      if (Sakan.u.getContactNum().equalsIgnoreCase("1")) {
                          mainfunc();

                      }


              }








                   tst = connection.prepareStatement("INSERT INTO USERS(EMAIL,USERNAME,PASSWORD,contact_num,user_type) VALUES" + "(?,?,?,?,?)");

                   tst.setString(1, u.getEmail());
                   tst.setString(2, u.getUsername());
                   tst.setString(3, u.getPassword());
                   tst.setString(4, u.getContactNum());
                   tst.setString(5, usertype);
                   tst.executeUpdate();


               logger.info("Signed  up successfully...");

               if(usertype.equalsIgnoreCase(owners)){


                   tst = connection.prepareStatement("SELECT USER_ID FROM USERS ORDER BY USER_ID DESC LIMIT 1");
                   rs=tst.executeQuery();
                   if(rs.next()){
                       Sakan.u.setUsersID(rs.getInt(1));
                   }

               }


                if(usertype.equalsIgnoreCase(owners)) {
                    Sakan.onlineUser = u.getEmail();
                    checklogin(u.getEmail(), u.getPassword(),usertype);
                    Sakan.flagOwner=1;

                }

               Sakan.flag1 = 1;


           Sakan.onlineUser = u.getEmail();
           Sakan.flag1 = 1;


      }
    }






    public static void viewfurnituresfunc(String Fdescription , int FurnitureID) throws SQLException {

        Scanner st = new Scanner(System.in);
        Scanner sc=new Scanner(System.in);

      ;
        PreparedStatement pst= null;
        ResultSet rs = null;
        String view1;
        while(true) {
            String desc = "\t            ★★  " + Fdescription + "  ★★                 \t";
            logger.info(desc);
            logger.info("(A) View pictures of the selected furniture        ");
            logger.info("(B) To purchase the selected furniture             ");
            logger.info("(C) Back                                           ");
            logger.info("(D) Main menu (Log out)                           ");


            view1  = sc.nextLine();

            if (view1.equalsIgnoreCase("A")) {

                 try {
                     pst = connection.prepareStatement("SELECT picture FROM furniture_pic  WHERE " + FurnitureID + " = furniture_id  ");
                     rs = pst.executeQuery();
                     while (rs.next()) {

                         String content = s1 + rs.getString(1) + s1;
                         logger.info(content);


                     }


                 }catch (SQLException e){

                 }finally {
                     try {
                         if (rs != null){
                             rs.close();
                         }
                     }finally {
                         if (pst != null) {
                             pst.close();
                         }
                     }
                 }


            }



            if (view1.equalsIgnoreCase("B")) {


                String confirm;



                        while (true) {
                            Sakan.whileflag = 0;
                            Sakan.whileflag2 = 0;


                            while(true){

                                Sakan.whileflag2=0;
                                logger.info("Are you sure you want to purchase this Furniture?");
                                logger.info("(A) Confirm   (B) Cancel");
                                confirm = st.nextLine();
                                if(confirm.equalsIgnoreCase("A")){



                                  try {
                                      pst = connection.prepareStatement("UPDATE furniture SET status='sold' WHERE furniture_id = '" + FurnitureID + "'");
                                      pst.executeUpdate();

                                  }catch (SQLException e){

                                  }finally {
                                      try {
                                          if (rs != null){
                                              rs.close();
                                          }
                                      }finally {
                                          if (pst != null) {
                                              pst.close();
                                          }
                                      }
                                  }



                                  try{
                                      pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                      pst.setString(1, Sakan.onlineUser + " has purchased a furniture (" + Sakan.f.getFurnitureDescription() + ")");

                                      pst.executeUpdate();

                                  }catch (SQLException e){

                                  }finally {
                                      try {
                                          if (rs != null){
                                              rs.close();
                                          }
                                      }finally {
                                          if (pst != null) {
                                              pst.close();
                                          }
                                      }
                                  }
                                        logger.info("Furniture purchased successfully!");
                                        Sakan.flag2 = 1;
                                        Sakan.whileflag2 = 1;
                                        tenantfunc(tenants);




                                }
                                else if(confirm.equalsIgnoreCase("B")){
                                    Sakan.whileflag = 1;
                                    break;
                                }
                                else logger.info("Invalid input");
                            }
                            if(Sakan.whileflag == 1){break;}
                        }





                }
            else if (view1.equalsIgnoreCase("C")) {
                Sakan.flag2 = 1;
                tenantfunc(tenants);
            }

            else if (view1.equalsIgnoreCase("D")) {
                mainfunc();

            }


            }


        }



    public static void viewfloorsfunc( int floorID,int buildingID, String Temail) throws SQLException {
        Scanner st = new Scanner(System.in);
        Scanner sc=new Scanner(System.in);

        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;
        String view1;

        int sum=0;
        PreparedStatement ust= null;
        ResultSet us = null;
while(true) {
    logger.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
    String msg = "\t      ★★  " + "Apartment(" + floorID + ")" + "  ★★      \t";
    logger.info(msg);

    logger.info("(A) View pictures of the selected floor     ");
    logger.info("(B) View the people living in the selected floor");
    logger.info("(C) To book the selected floor                 ");
    logger.info("(D) Back                                     ");
    logger.info("(E) Main menu (Log out)                     ");


    view1  = sc.nextLine();

    if (view1.equalsIgnoreCase("A")) {



            pst = connection.prepareStatement("SELECT picture FROM house_pic  WHERE " + floorID + " = floor_id  ");
            rs = pst.executeQuery();
            while (rs.next()) {

                String content = s1 + rs.getString(1) + s1;
                logger.info(content);


            }





    }

    if (view1.equalsIgnoreCase("B")) {


            int flagpart = 0;
            pst = connection.prepareStatement("SELECT * FROM house_participants  WHERE " + floorID + " = floor_id  ");
            rs = pst.executeQuery();


            while (rs.next()) {
                flagpart =1;
                String content = "\t| Name: \t" + rs.getString(3) + "\t| Age: \t" + rs.getString(4) + "\t| Major: \t" + rs.getString(5) + "\t| Gender: \t" + rs.getString(6) + s1;
                logger.info(content);


            }
            if(flagpart==0){
                logger.info("No one is living in this apartment currently");
            }






    }

    if (view1.equalsIgnoreCase("C")) {

        String gender;
        String backb;
        int ageb;
        String ageSTR;
        String majorb;
        String confirm;
        String nameb = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setMonth(timestamp.getMonth()+1);






            pst = connection.prepareStatement("SELECT building_name FROM building WHERE building_id = '" + buildingID + "'" );
            rs = pst.executeQuery();
            if(rs.next()){
                Sakan.b.setBuildingName(rs.getString(1));
            }




            pst = connection.prepareStatement("SELECT * FROM floors  WHERE " + floorID + " = floor_id AND max_participants > 1 ");
            rs = pst.executeQuery();


            if (rs.next()) {
                while (true) {
                Sakan.whileflag = 0;
                    Sakan.whileflag2 = 0;

                    logger.info("(A) To go back");


                    logger.info("This is a student housing.. Please fill the following data");

                    logger.info("Please select your gender");
                    logger.info("  (M) Male . (F) Female");

                 backb = st.nextLine();
                if (backb.equalsIgnoreCase("A")) {
                    Sakan.whileflag = 1;
                    break;
                } else if (backb.equalsIgnoreCase("M") || backb.equalsIgnoreCase("F")) {
                     Sakan.hp.setPartGender( backb);
                } else continue;

                    logger.info("Please Enter your age: ");
               ageSTR = st.nextLine();
                    if (ageSTR.equalsIgnoreCase("A")) {
                        Sakan.whileflag = 1;
                        break;
                    }

               if(isNumber(ageSTR)){
                   Sakan.hp.setPartAge(Integer.parseInt(ageSTR));
               }
               else continue;

                    logger.info("Please Enter your Major: ");


              Sakan.hp.setPartMajor(st.nextLine());
                    if (Sakan.hp.getPartMajor().equalsIgnoreCase("A")) {
                        Sakan.whileflag = 1;
                        break;
                    }

                    while(true){
                        Sakan.whileflag2=0;
                        logger.info("Your data is ready.. are you sure you want to continue?");
                        logger.info("(A) Confirm   (B) Cancel");
                        confirm = st.nextLine();
                        if(confirm.equalsIgnoreCase("A")){




                                pst = connection.prepareStatement("INSERT INTO house_participants(floor_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,floorID );
                                pst.setString(2,Sakan.u.getUsername() );
                                pst.setInt(3, Sakan.hp.getPartAge());
                                pst.setString(4, Sakan.hp.getPartMajor());
                                pst.setString(5, Sakan.hp.getPartGender() );
                                pst.executeUpdate();



                                pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                pst.setString(1, Sakan.onlineUser + " has reserved an apartment in the building (" + Sakan.b.getBuildingName() + ")");

                                pst.executeUpdate();


                                pst = connection.prepareStatement("UPDATE floors SET participants = (participants+1) WHERE floor_id = '" + floorID + "'");
                                pst.executeUpdate();

                                tst = connection.prepareStatement("SELECT PARTICIPANTS FROM FLOORS WHERE BUILDING_ID= '"+buildingID+"'");

                                ts=tst.executeQuery();

                                while(ts.next()){

                                sum+=ts.getInt(1);
                                }

                                ust = connection.prepareStatement("UPDATE BUILDING SET TOTALPARTICIPANTS='"+sum+"' WHERE BUILDING_ID= '"+buildingID+"'");

                               ust.executeUpdate();



                                pst = connection.prepareStatement("SELECT floor_id FROM floors WHERE participants = max_participants AND FLOOR_ID='"+floorID+"' ");
                                rs = pst.executeQuery();

                                if(rs.next()){

                                    pst = connection.prepareStatement("UPDATE floors SET availability = 'unavailable' WHERE floor_id = '" + floorID + "'");
                                    pst.executeUpdate();

                                    pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                    pst.setString(1, " The apartment with the id ("+ floorID+ ") in the building (" + Sakan.b.getBuildingName() + ") is full");

                                    pst.executeUpdate();

                                }

                                logger.info("Apartment booked successfully!");

                                Sakan.bi.setRentDate(sdf.format((timestamp)));

                                pst=connection.prepareStatement("SELECT OWNER_NAME,CONTACT_NUM FROM BUILDING WHERE BUILDING_ID='"+buildingID+"'");
                                rs= pst.executeQuery();
                                if(rs.next()){
                                    Sakan.bi.setOwnerName(rs.getString(1));
                                    Sakan.bi.setContactInfo(rs.getInt(2));
                                }


                                pst=connection.prepareStatement("INSERT INTO BOOKING_INFO(TENANT_NAME,OWNER_NAME,CONTACT_INFO,RENT_DATE,TENANT_ID) VALUES (?,?,?,?,?)");
                                pst.setString(1,Sakan.u.getUsername());
                                pst.setString(2,Sakan.bi.getOwnerName());
                                pst.setInt(3,Sakan.bi.getContactInfo());
                                pst.setString(4,sdf.format(timestamp));
                                pst.setInt(5,Sakan.bi.getTenantId());
                                pst.executeUpdate();




                                Sakan.whileflag2 = 1;
                                Sakan.flag2 = 1;
                                tenantfunc(tenants);



                        }
                        else if(confirm.equalsIgnoreCase("B")){
                            Sakan.whileflag = 1;
                            break;
                        }
                        else logger.info("Invalid input");
                    }

                   if(Sakan.whileflag2 == 1){

                       break;
                   }

            }
                if(Sakan.whileflag == 1){continue;}


            }
           else {

                while (true) {
                    Sakan.whileflag = 0;
                    Sakan.whileflag2 = 0;


                    while(true){
                        ageb = 0;
                        majorb = "Unknown";
                        gender = "Unknown";
                        Sakan.whileflag2=0;
                        logger.info("Your data is ready.. are you sure you want to continue?");
                        logger.info("(A) Confirm   (B) Cancel");
                        confirm = st.nextLine();
                        if(confirm.equalsIgnoreCase("A")){


                                pst = connection.prepareStatement("SELECT USER_ID,username FROM users WHERE email = '" + Temail + "'" );
                                rs = pst.executeQuery();

                                if(rs.next()){
                                    Sakan.bi.setTenantId(rs.getInt(1));
                                    nameb = rs.getString(2);
                                    logger.info(Sakan.u.getUsername());
                                }
                                pst = connection.prepareStatement("INSERT INTO house_participants(floor_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,floorID );
                                pst.setString(2,nameb );
                                pst.setInt(3, ageb);
                                pst.setString(4, majorb);
                                pst.setString(5, gender );

                                pst.executeUpdate();

                                pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                pst.setString(1, Sakan.onlineUser + " has reserved an apartment in the building (" + Sakan.b.getBuildingName() + ")");

                                pst.executeUpdate();


                                pst = connection.prepareStatement("UPDATE floors SET participants = (participants+1) WHERE floor_id = '" + floorID + "'");
                                pst.executeUpdate();

                                tst = connection.prepareStatement("SELECT PARTICIPANTS FROM FLOORS WHERE BUILDING_ID= '"+buildingID+"'");

                                ts=tst.executeQuery();

                                while(ts.next()){

                                    sum+=ts.getInt(1);
                                }

                                ust = connection.prepareStatement("UPDATE BUILDING SET TOTALPARTICIPANTS='"+sum+"' WHERE BUILDING_ID= '"+buildingID+"'");

                                ust.executeUpdate();




                                pst = connection.prepareStatement("SELECT floor_id FROM floors WHERE participants = max_participants AND FLOOR_ID='"+floorID+"' ");
                                rs = pst.executeQuery();

                                if(rs.next()){

                                    pst = connection.prepareStatement("UPDATE floors SET availability = 'unavailable' WHERE floor_id = '" + floorID + "'");
                                    pst.executeUpdate();

                                    pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                    pst.setString(1, " The apartment with the id ("+ floorID+ ") in the building (" + Sakan.b.getBuildingName() + ") is full");

                                    pst.executeUpdate();


                                }

                                logger.info("Apartment booked successfully!");



                              Sakan.bi.setRentDate(sdf.format((timestamp)));

                                pst=connection.prepareStatement("INSERT INTO BOOKING_INFO(TENANT_NAME,OWNER_NAME,CONTACT_INFO,RENT_DATE,TENANT_ID) VALUES (?,?,?,?,?)");
                                pst.setString(1,nameb);
                                pst.setString(2,Sakan.bi.getOwnerName());
                                pst.setInt(3,Sakan.bi.getContactInfo());
                                pst.setString(4,sdf.format(timestamp));
                                pst.setInt(5,Sakan.bi.getTenantId());
                                pst.executeUpdate();

                                Sakan.flag2 = 1;
                                Sakan.whileflag2 = 1;
                                tenantfunc(tenants);




                        }
                        else if(confirm.equalsIgnoreCase("B")){
                            Sakan.whileflag = 1;
                            break;
                        }
                        else logger.info("Invalid input");
                    }
                    if(Sakan.whileflag == 1){break;}
                }
                if(Sakan.whileflag == 1){continue;}



            }





    }

    if (view1.equalsIgnoreCase("D")) {
        Sakan.flag2 = 1;
        tenantfunc(tenants);
    }

    else if (view1.equalsIgnoreCase("E")) {
        mainfunc();

    }
}
    }



    static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }

    public static void main(String []args) throws SQLException {

       mainfunc();

}


}
