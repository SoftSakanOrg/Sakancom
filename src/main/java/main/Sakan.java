package main;//import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.System.exit;
import static java.lang.System.getLogger;
import static main.funcCheckEmail.checkemail;
import static main.funcCheckLogin.checklogin;
import static main.funcOwner.ownerfunc;
import static main.funcUsersLogin.Login;
import java.util.logging.Logger;

public class Sakan {

    public static int flag1 = 0;
    public static int flag11 = 0;
    public static int flag2 = 0;

    public static int whileflag = 0;
    public static int whileflag2 = 0;

    public static int flagnext = 0;

    public static  int flaglogin = 0;

    public static  int flagRequestAction = 0;

    public static  int flagSelectRequest = 0;

    public static  int flagAdminFunc = 0;

    public static  int flagSelectMyFloor = 0;
    public static  int flagSelectBuilding = 0;

    public static  int flagOwner = 0;

    public static  int flagReflect = 0;




    public static String OnlineUser = "" ;

    public static Users U=new Users();

    public static Tenant T=new Tenant();


    static Floors H=new Floors();
    static Furniture F=new Furniture();

    static houseParticipants hp=new houseParticipants();
    static housePictures hpc=new housePictures();
    static furniturePictures fpc=new furniturePictures();

    static Buildings B=new Buildings();

    static advertismentRequests ar = new advertismentRequests();

    static  systemObservation so = new systemObservation();
    static bookingInfo bi=new bookingInfo();

    static Users Login=new Users();

    static Logger logger =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void Mainfunc(){
           Sakan.flag1 =0;
           Sakan.flag2 = 0;
           Sakan.OnlineUser = "";
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
                tenantfunc("TENANTS");


            }
            else if(c.equalsIgnoreCase("2")){
                tenantfunc("OWNERS");


            }

            else if(c.equalsIgnoreCase("3")){
                tenantfunc("ADMIN");


            }
            else if(c.equalsIgnoreCase("exit")){
                exit(0);
            }
            else{
                logger.info("Please make sure to enter the right user........");
             //   System.out.print("\n");

            }


        }
    }

    public static void tenantfunc(String usertype){
        Scanner sc=new Scanner(System.in);
        String temp;
          if(Sakan.flaglogin==1)temp="2";
       else temp="3";





        if(Sakan.flag2==0){
            if(Sakan.flaglogin==0) {


                if (!usertype.equalsIgnoreCase("ADMIN")) {

                    logger.info("(1) Sign up          ");}
                    logger.info("(2) Log in           ");
                    logger.info("(3) Back to main menu");

            }

      if(Sakan.flaglogin==0)
        temp=sc.nextLine();

        if(temp.equalsIgnoreCase("3")){
            Mainfunc();

        }

        else if(temp.equalsIgnoreCase("1") && !usertype.equalsIgnoreCase("ADMIN")) {
            Signup(usertype);


        }

        else if(temp.equalsIgnoreCase("2")) {



            logger.info("(1) To go back to main menu");

            logger.info("Enter your email: ");
            Sakan.U.setEmail(sc.nextLine());

            logger.info("Enter password: ");
            Sakan.U.setPassword(sc.nextLine());


            Login(usertype,Sakan.U.getEmail(),Sakan.U.getPassword());


        }
       }
        Scanner sv = new Scanner(System.in);
        String view;

         if(usertype.equalsIgnoreCase("TENANTS")){
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
              addfurniture(Sakan.OnlineUser);

           }  else if(view.equalsIgnoreCase("G")){
               viewBookingInfo(Sakan.U.getUsersID());
           }

           else if (view.equalsIgnoreCase("H")) {
               Mainfunc();

           }

       }
    }
    }



    public static void tenantId(){

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT user_id,USERNAME FROM USERS WHERE EMAIL='" + Sakan.OnlineUser + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                Sakan.bi.setTenantId(rs.getInt(1));
                Sakan.U.setUsername(rs.getString(2));
                Sakan.U.setUsersID(rs.getInt(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();

        }
    }

public static void viewBookingInfo(int tenant_id){

    Connection connection = null;
    PreparedStatement pst= null;
    ResultSet rs = null;


    try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("SELECT * FROM BOOKING_INFO WHERE TENANT_ID = '" +tenant_id+"'");
        rs = pst.executeQuery();
        if(!rs.next()){
            logger.info("You don`t have any rented apartment yet...");
        }
        rs= pst.executeQuery();
        while (rs.next()) {

            String content = "\t|\t Tenant_Name: " + rs.getString(2) + "\t|\t Owner_Name: "+ rs.getString(3)+ "\t|\t Contact_info: "+ rs.getInt(4)+ "\t|\t Rent_Date: "+ rs.getString(5)+ "\t|\t ";
            logger.info(content);
        }


    } catch (SQLException e) {
        e.printStackTrace();

    }



}



    public static void viewfloor(){
        Connection connection = null;
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM floors WHERE availability = 'available' AND Status = 'Advertised' ");
            tst = connection.prepareStatement("SELECT B.location,B.building_name FROM floors F,building B WHERE F.building_id = B.building_id ");
            rs = pst.executeQuery();
            ts = tst.executeQuery();
            while (rs.next()) {
                ts.next();
                String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t availability: "+ rs.getString(3)+ "\t|\t Price: "+ rs.getInt(4)+ "\t|\t Services: "+ rs.getString(5)+ "\t|\t Participants: "+ rs.getInt(6)+ "\t|\t Max_Participants: "+ rs.getInt(7)+ "\t|\t BedroomsNum: "+ rs.getInt(8)+ "\t|\t BathroomsNum: "+ rs.getInt(9)+ "\t|\t Balcony: "+ rs.getInt(10)+ "\t|\t Status: "+ rs.getString(11);
                logger.info(content);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void selectfloor(){
        Scanner sc=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;
        logger.info("Enter the floor ID: ");

        Sakan.H.setHouseId(sc.nextInt());

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM floors WHERE floor_id = '" +  Sakan.H.getHouseId() + "' AND availability = 'available' AND Status = 'Advertised'"  );
            tst= connection.prepareStatement("SELECT building_id FROM floors WHERE floor_id = '" +  Sakan.H.getHouseId() + "' AND availability = 'available'  AND Status = 'Advertised'" );
            ts = tst.executeQuery();
            rs = pst.executeQuery();

            if(ts.next()){
                Sakan.B.setBuildingId(ts.getInt(1));
            }

            if (rs.next()) {
              // Sakan.H.setHouseName(rs.getString(2));
                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + "\t|\t";
                logger.info(content);
                viewfloorsfunc(Sakan.H.getHouseId(),Sakan.B.getBuildingId(),Sakan.OnlineUser);


            }
            else if(!rs.next()){
                logger.info("Please enter a valid floor ID...");

            }




        } catch (SQLException e) {
            e.printStackTrace();

        }




    }

    public static void viewfurniture(){
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM furniture WHERE status = 'forsale' ");
            rs = pst.executeQuery();
            while (rs.next()) {
                String content = "\t|\t ID: " + rs.getInt(1) + " \t|\t price: " + rs.getInt(3) + "\t|\t description: " + rs.getString(4) + "\t|\t status: " + rs.getString(5) + "\t|\t" ;
                logger.info(content);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void selectfurniture(){
        Scanner sc=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        logger.info("Enter the furniture ID: ");

       Sakan.F.setFurnitureID(sc.nextInt());

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM furniture WHERE furniture_id = '" + Sakan.F.getFurnitureID() + "' AND status = 'forsale'" );
            rs = pst.executeQuery();
            if (rs.next()) {
               Sakan.F.setFurnitureDescription(rs.getString(4));
                String content = "\t|\t ID: " + rs.getInt(1) + " \t|\t price: " + rs.getInt(3) + "\t|\t description: " + rs.getString(4) + "\t|\t";
                logger.info(content);
                viewfurnituresfunc(  Sakan.F.getFurnitureDescription(),Sakan.F.getFurnitureID());


            }
            else if(!rs.next()){
                logger.info("Please enter a valid furniture ID...");

            }


        } catch (SQLException e) {
            e.printStackTrace();

        }




    }

    public static void  addfurniture(String Temail){
        Scanner sf=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
       // int TenantID=0;
        Sakan.U.setUsersID(0);

        int furID=0;
        Scanner scon=new Scanner(System.in);
        int furIDVal=0;

       Sakan.F.setFurnitureStatus("forsale");




        logger.info("(1) Back                                           ");


        logger.info(" Enter description: ");

       Sakan.F.setFurnitureDescription(sf.nextLine());

       if(Sakan.F.getFurnitureDescription().equalsIgnoreCase("1")){
           Sakan.flag2 = 1;
           tenantfunc("TENANTS");
       }




        int price;

        logger.info(" Enter price: ");
         Sakan.F.setFurniturePrice(sf.nextInt());

        if(String.valueOf(Sakan.F.getFurniturePrice()).equalsIgnoreCase("1")){
            Sakan.flag2 = 1;
            tenantfunc("TENANTS");
        }


     try {

         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("SELECT user_id FROM USERS WHERE EMAIL='" + Temail + "'");
         rs = pst.executeQuery();

         if (rs.next()) {
             Sakan.U.setUsersID(rs.getInt(1));
         }

         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("INSERT INTO FURNITURE(user_ID,PRICE,DESCRIPTION,STATUS) VALUES (?,?,?,?)");

         pst.setInt(1,  Sakan.U.getUsersID());
         pst.setInt(2, Sakan.F.getFurniturePrice());
         pst.setString(3, Sakan.F.getFurnitureDescription());
         pst.setString(4, Sakan.F.getFurnitureStatus());

         pst.executeUpdate();

         pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

         pst.setString(1, Sakan.OnlineUser + " has added a new furniture (" + Sakan.F.getFurnitureDescription() + "," + Sakan.F.getFurniturePrice() + "$)");

         pst.executeUpdate();

         logger.info("You have successfully added your furnitures...");


     } catch (SQLException e) {
         e.printStackTrace();

     }
     try{
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("SELECT FURNITURE_ID FROM FURNITURE ORDER BY FURNITURE_ID DESC LIMIT 1");
         rs= pst.executeQuery();

         if(rs.next()){
          Sakan.F.setFurnitureID(rs.getInt(1));
         }

     }
     catch (SQLException e) {
         e.printStackTrace();

     }
     try {
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("SELECT FURNITURE_ID FROM FURNITURE WHERE FURNITURE_ID='" + Sakan.F.getFurnitureID() + "'");
         rs = pst.executeQuery();
         if (rs.next()) {
             Sakan.F.setFurnitureID(rs.getInt(1));
         }
     } catch (SQLException e) {
         e.printStackTrace();

     }
        logger.info("Do you want to add  pictures for this furniture?");
        logger.info("A) yes . B) No");
     String ans = scon.nextLine();

     if (ans.equalsIgnoreCase("A")) {
         logger.info(" Enter pictures: ");

         Sakan.fpc.setFurniturePicture(scon.nextLine());
         if ( Sakan.fpc.getFurniturePicture().equalsIgnoreCase("1")) {
             Sakan.flag2 = 1;
             tenantfunc("TENANTS");
         }

         try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
             pst = connection.prepareStatement("INSERT INTO FURNITURE_PIC(FURNITURE_ID,PICTURE) VALUES (?,?)");
             pst.setInt(1, Sakan.F.getFurnitureID());
             pst.setString(2, Sakan.fpc.getFurniturePicture());
             pst.executeUpdate();

             pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

             pst.setString(1, Sakan.OnlineUser + " has added a new picture (" + Sakan.fpc.getFurniturePicture() + " to the furniture with ID: (" + Sakan.F.getFurnitureID()+ ")");

             pst.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();

         }
         logger.info("Pictures have been added");
     }


     Sakan.flag2 = 1;
     tenantfunc("TENANTS");

    }

    public static void Signup(String usertype){


      if (Sakan.flag1 == 0) {
          Scanner sc = new Scanner(System.in);

          Connection connection = null;
          PreparedStatement pst = null;
          ResultSet rs = null;
          PreparedStatement tst= null;


          logger.info("(1) To go back to main menu");





          logger.info("Enter your email: ");


              Sakan.U.setEmail(sc.nextLine());

          if (Sakan.U.getEmail().equalsIgnoreCase("1")) {
              Mainfunc();

          }
          if (!Sakan.U.getEmail().contains("@") || !U.getEmail().contains(".")) {
              logger.info("Please enter a valid email...");
              Signup(usertype);
          }



          checkemail(Sakan.U.getEmail(), 1, usertype);
              if(Sakan.flag1==0) {

                  logger.info("Enter username: ");
                  Sakan.U.setUsername(sc.nextLine());
                  if (Sakan.U.getUsername().equalsIgnoreCase("1")) {
                      Mainfunc();

                  }



                  logger.info("Enter password: ");
                  Sakan.U.setPassword(sc.nextLine());

                  if (Sakan.U.getPassword().equalsIgnoreCase("1")) {
                      Mainfunc();

                  }

                  logger.info("Enter Contact Number: ");
                      Sakan.U.setContactNum(sc.nextLine());

                      if (Sakan.U.getContactNum().equalsIgnoreCase("1")) {
                          Mainfunc();

                      }


              }




           try {


                   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                   tst = connection.prepareStatement("INSERT INTO USERS(EMAIL,USERNAME,PASSWORD,contact_num,user_type) VALUES" + "(?,?,?,?,?)");

                   tst.setString(1, U.getEmail());
                   tst.setString(2, U.getUsername());
                   tst.setString(3, U.getPassword());
                   tst.setString(4, U.getContactNum());
                   tst.setString(5, usertype);
                   tst.executeUpdate();


               logger.info("Signed  up successfully...");

               if(usertype.equalsIgnoreCase("owners")){

                   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                   tst = connection.prepareStatement("SELECT USER_ID FROM USERS ORDER BY USER_ID DESC LIMIT 1");
                   rs=tst.executeQuery();
                   if(rs.next()){
                       Sakan.U.setUsersID(rs.getInt(1));
                   }

               }


                if(usertype.equalsIgnoreCase("OWNERS")) {
                    Sakan.OnlineUser=U.getEmail();
                    checklogin(U.getEmail(),U.getPassword(),usertype);
                    Sakan.flagOwner=1;
                   // ownerfunc("OWNERS", Sakan.U.getUsersID());
                }

               Sakan.flag1 = 1;

           } catch (SQLException e) {
               e.printStackTrace();

           }
           Sakan.OnlineUser=U.getEmail();
           Sakan.flag1 = 1;


      }
    }






    public static void viewfurnituresfunc(String Fdescription , int FurnitureID) {

        Scanner st = new Scanner(System.in);
        Scanner sc=new Scanner(System.in);

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        String view1;
        while(true) {
            logger.info("\t            ★★  " + Fdescription + "  ★★                 \t");
            logger.info("(A) View pictures of the selected furniture        ");
            logger.info("(B) To purchase the selected furniture             ");
            logger.info("(C) Back                                           ");
            logger.info("(D) Main menu (Log out)                           ");


            view1  = sc.nextLine();

            if (view1.equalsIgnoreCase("A")) {

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("SELECT picture FROM furniture_pic  WHERE " + FurnitureID + " = furniture_id  ");
                    rs = pst.executeQuery();
                    while (rs.next()) {

                        String content = "\t|\t" + rs.getString(1) + "\t|\t";
                        logger.info(content);


                    }


                } catch (SQLException e) {
                    e.printStackTrace();

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


                                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                                        pst = connection.prepareStatement("UPDATE furniture SET status='sold' WHERE furniture_id = '" + FurnitureID + "'");
                                        pst.executeUpdate();

                                        pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                        pst.setString(1, Sakan.OnlineUser + " has purchased a furniture (" + Sakan.F.getFurnitureDescription() + ")");

                                        pst.executeUpdate();

                                        logger.info("Furniture purchased successfully!");
                                        Sakan.flag2 = 1;
                                        Sakan.whileflag2 = 1;
                                        tenantfunc("TENANTS");

                                        // break;
                                    } catch (SQLException e) {
                                        e.printStackTrace();

                                    }

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
                tenantfunc("TENANTS");
            }

            else if (view1.equalsIgnoreCase("D")) {
                Mainfunc();

            }


            }


        }



    public static void viewfloorsfunc( int floorID,int buildingID, String Temail) {
        Scanner st = new Scanner(System.in);
        Scanner sc=new Scanner(System.in);

        Connection connection = null;
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
    logger.info("\t      ★★  "+ "Apartment("+floorID+")" +"  ★★      \t");

    logger.info("(A) View pictures of the selected floor     ");
    logger.info("(B) View the people living in the selected floor");
    logger.info("(C) To book the selected floor                 ");
    logger.info("(D) Back                                     ");
    logger.info("(E) Main menu (Log out)                     ");


    view1  = sc.nextLine();

    if (view1.equalsIgnoreCase("A")) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT picture FROM house_pic  WHERE " + floorID + " = floor_id  ");
            rs = pst.executeQuery();
            while (rs.next()) {

                String content = "\t|\t" + rs.getString(1) + "\t|\t";
                logger.info(content);


            }


        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    if (view1.equalsIgnoreCase("B")) {

        try {
            int flagpart = 0;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM house_participants  WHERE " + floorID + " = floor_id  ");
            rs = pst.executeQuery();


            while (rs.next()) {
                flagpart =1;
                String content = "\t| Name: \t" + rs.getString(3) + "\t| Age: \t" + rs.getString(4) + "\t| Major: \t" + rs.getString(5) + "\t| Gender: \t" + rs.getString(6) + "\t|\t";
                logger.info(content);


            }
            if(flagpart==0){
                logger.info("No one is living in this apartment currently");
            }



        } catch (SQLException e) {
            e.printStackTrace();

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

        try {


            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");

            pst = connection.prepareStatement("SELECT building_name FROM building WHERE building_id = '" + buildingID + "'" );
            rs = pst.executeQuery();
            if(rs.next()){
                Sakan.B.setBuildingName(rs.getString(1));
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
                            try {


                                pst = connection.prepareStatement("INSERT INTO house_participants(floor_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,floorID );
                                pst.setString(2,Sakan.U.getUsername() );
                                pst.setInt(3, Sakan.hp.getPartAge());
                                pst.setString(4, Sakan.hp.getPartMajor());
                                pst.setString(5, Sakan.hp.getPartGender() );
                                pst.executeUpdate();



                                pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                pst.setString(1, Sakan.OnlineUser + " has reserved an apartment in the building (" + Sakan.B.getBuildingName() + ")");

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

                                    pst.setString(1, " The apartment with the id ("+ floorID+ ") in the building (" + Sakan.B.getBuildingName() + ") is full");

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
                                pst.setString(1,Sakan.U.getUsername());
                                pst.setString(2,Sakan.bi.getOwnerName());
                                pst.setInt(3,Sakan.bi.getContactInfo());
                                pst.setString(4,sdf.format(timestamp));
                                pst.setInt(5,Sakan.bi.getTenantId());
                                pst.executeUpdate();




                                Sakan.whileflag2 = 1;
                                Sakan.flag2 = 1;
                                tenantfunc("TENANTS");
                             //  break;
                            } catch (SQLException e) {
                                e.printStackTrace();

                            }

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
                            try {
                                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                                pst = connection.prepareStatement("SELECT USER_ID,username FROM users WHERE email = '" + Temail + "'" );
                                rs = pst.executeQuery();

                                if(rs.next()){
                                    Sakan.bi.setTenantId(rs.getInt(1));
                                    nameb = rs.getString(2);
                                     System.out.print(Sakan.U.getUsername());
                                }
                                pst = connection.prepareStatement("INSERT INTO house_participants(floor_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,floorID );
                                pst.setString(2,nameb );
                                pst.setInt(3, ageb);
                                pst.setString(4, majorb);
                                pst.setString(5, gender );

                                pst.executeUpdate();

                                pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                pst.setString(1, Sakan.OnlineUser + " has reserved an apartment in the building (" + Sakan.B.getBuildingName() + ")");

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

                                    pst.setString(1, " The apartment with the id ("+ floorID+ ") in the building (" + Sakan.B.getBuildingName() + ") is full");

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
                                tenantfunc("TENANTS");

                               // break;
                            } catch (SQLException e) {
                                e.printStackTrace();

                            }

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



        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    if (view1.equalsIgnoreCase("D")) {
        Sakan.flag2 = 1;
        tenantfunc("TENANTS");
    }

    else if (view1.equalsIgnoreCase("E")) {
        Mainfunc();

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

    public static void main(String []args){

       Mainfunc();

}


}
