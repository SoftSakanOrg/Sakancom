package main;//import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.System.exit;
import static java.lang.System.getLogger;
import static main.funcCheckEmail.checkemail;
import static main.funcUsersLogin.Login;

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


    public static void Mainfunc(){
           Sakan.flag1 =0;
           Sakan.flag2 = 0;
           Sakan.OnlineUser = "";
           Sakan.flag11 = 0;
           Sakan.flaglogin=0;

        Scanner sc=new Scanner(System.in);

        String c ;

        while (true)
        {

            System.out.println( "███████████████" );
            System.out.println( "██(1) Tenant ██" );
            System.out.println( "███████████████" );
            System.out.println( "██(2) Owner  ██" );
            System.out.println( "███████████████" );
            System.out.println( "██(3) Admin  ██" );
            System.out.println( "███████████████" );
            System.out.print( "Choose between the specific users :  " );
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
                System.out.println("Please make sure to enter the right user........");
                System.out.print("\n");

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
                    System.out.println("█████████████████████████");
                    System.out.println("██(1) Sign up          ██");}
                    System.out.println("█████████████████████████");
                    System.out.println("██(2) Log in           ██");
                    System.out.println("█████████████████████████");
                    System.out.println("██(3) Back to main menu██");
                    System.out.println("█████████████████████████");
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

           // Scanner sc = new Scanner(System.in);


          //  Scanner sc = new Scanner(System.in);
            System.out.println("███████████████████████████████");
            System.out.println("██(1) To go back to main menu██");
            System.out.println("███████████████████████████████");

            System.out.print("Enter your email: ");
            Sakan.U.setEmail(sc.nextLine());

            System.out.print("Enter password: ");
            Sakan.U.setPassword(sc.nextLine());


            Login(usertype,Sakan.U.getEmail(),Sakan.U.getPassword());


        }
       }
        Scanner sv = new Scanner(System.in);
        String view;
//        if(usertype.equalsIgnoreCase("OWNERS")){
//            ownerfunc("OWNERS");
//        }

         if(usertype.equalsIgnoreCase("TENANTS")){
       while(true) {

           tenantId();

           System.out.println("\nHere is a menu showing the available options:-");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(A) View available Apartments                      ██");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(B) To Select one of the available floors using ID ██");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(C) View furnitures for sale                       ██");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(E) To Select a furniture to buy using ID          ██");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(F) Advertise a furniture for sale                 ██");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(G) View personal info of booking                  ██");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(H) Main menu (Log out)                            ██");
           System.out.println("███████████████████████████████████████████████████████");
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
            System.out.println("You don`t have any rented apartment yet...");
        }
        rs= pst.executeQuery();
        while (rs.next()) {

            String content = "\t|\t Tenant_Name: " + rs.getString(2) + "\t|\t Owner_Name: "+ rs.getString(3)+ "\t|\t Contact_info: "+ rs.getInt(4)+ "\t|\t Rent_Date: "+ rs.getString(5)+ "\t|\t ";
            System.out.println(content);
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
                System.out.println(content);
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
        System.out.print("Enter the floor ID: ");

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
                System.out.println(content);
                viewfloorsfunc(Sakan.H.getHouseId(),Sakan.B.getBuildingId(),Sakan.OnlineUser);


            }
            else if(!rs.next()){
                System.out.println("Please enter a valid floor ID...");

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
                System.out.println(content);
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
        System.out.print("Enter the furniture ID: ");

       Sakan.F.setFurnitureID(sc.nextInt());

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM furniture WHERE furniture_id = '" + Sakan.F.getFurnitureID() + "' AND status = 'forsale'" );
            rs = pst.executeQuery();
            if (rs.next()) {
               Sakan.F.setFurnitureDescription(rs.getString(4));
                String content = "\t|\t ID: " + rs.getInt(1) + " \t|\t price: " + rs.getInt(3) + "\t|\t description: " + rs.getString(4) + "\t|\t";
                System.out.println(content);
                viewfurnituresfunc(  Sakan.F.getFurnitureDescription(),Sakan.F.getFurnitureID());


            }
            else if(!rs.next()){
                System.out.println("Please enter a valid furniture ID...");

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



        System.out.println("███████████████████████████████████████████████████████");
        System.out.println("██(1) Back                                           ██");
        System.out.println("███████████████████████████████████████████████████████");

        System.out.println(" Enter description: ");
//        String description;
       Sakan.F.setFurnitureDescription(sf.nextLine());

       if(Sakan.F.getFurnitureDescription().equalsIgnoreCase("1")){
           Sakan.flag2 = 1;
           tenantfunc("TENANTS");
       }
      //   description=text;



        int price;

        System.out.println(" Enter price: ");
         Sakan.F.setFurniturePrice(sf.nextInt());

        if(String.valueOf(Sakan.F.getFurniturePrice()).equalsIgnoreCase("1")){
            Sakan.flag2 = 1;
            tenantfunc("TENANTS");
        }
//        price=tnum;

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

         System.out.println("You have successfully added your furnitures...");


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
     System.out.println("Do you want to add  pictures for this furniture?");
     System.out.println("A) yes . B) No");
     String ans = scon.nextLine();
//     String picture = null;
     if (ans.equalsIgnoreCase("A")) {
         System.out.println(" Enter pictures: ");

         Sakan.fpc.setFurniturePicture(scon.nextLine());
         if ( Sakan.fpc.getFurniturePicture().equalsIgnoreCase("1")) {
             Sakan.flag2 = 1;
             tenantfunc("TENANTS");
         }
//         picture = pictext;
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
         System.out.println("Pictures have been added");
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

          System.out.println("███████████████████████████████");
          System.out.println("██(1) To go back to main menu██");
          System.out.println("███████████████████████████████");


         // if(usertype.equalsIgnoreCase("TENANTS"))

          System.out.print("Enter your email: ");
//          String tempemail

              Sakan.U.setEmail(sc.nextLine());

          if (Sakan.U.getEmail().equalsIgnoreCase("1")) {
              Mainfunc();

          }
          if (!Sakan.U.getEmail().contains("@") || !U.getEmail().contains(".")) {
              System.out.println("Please enter a valid email...");
              Signup(usertype);
          }



          checkemail(Sakan.U.getEmail(), 1, usertype);
              if(Sakan.flag1==0) {

                  System.out.print("Enter username: ");
                  Sakan.U.setUsername(sc.nextLine());
                  if (Sakan.U.getUsername().equalsIgnoreCase("1")) {
                      Mainfunc();

                  }
//           name = tempname;


                  System.out.print("Enter password: ");
                  Sakan.U.setPassword(sc.nextLine());

                  if (Sakan.U.getPassword().equalsIgnoreCase("1")) {
                      Mainfunc();

                  }

                      System.out.print("Enter Contact Number: ");
                      Sakan.U.setContactNum(sc.nextLine());

                      if (Sakan.U.getContactNum().equalsIgnoreCase("1")) {
                          Mainfunc();

                      }


              }

         //  pass = temppass;


           try {


                   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                   tst = connection.prepareStatement("INSERT INTO USERS(EMAIL,USERNAME,PASSWORD,contact_num,user_type) VALUES" + "(?,?,?,?,?)");

                   tst.setString(1, U.getEmail());
                   tst.setString(2, U.getUsername());
                   tst.setString(3, U.getPassword());
                   tst.setString(4, U.getContactNum());
                   tst.setString(5, usertype);
                   tst.executeUpdate();


               System.out.println("Signed  up successfully...");

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
                    ownerfunc("OWNERS", Sakan.U.getUsersID());
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
            System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            System.out.println("\t            ★★  " + Fdescription + "  ★★                 \t");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(A) View pictures of the selected furniture        ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(B) To purchase the selected furniture             ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(C) Back                                           ██");
            System.out.println("███████████████████████████████████████████████████████");
            System.out.println("██(D) Main menu (Log out)                            ██");
            System.out.println("███████████████████████████████████████████████████████");

            view1  = sc.nextLine();

            if (view1.equalsIgnoreCase("A")) {

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                    pst = connection.prepareStatement("SELECT picture FROM furniture_pic  WHERE " + FurnitureID + " = furniture_id  ");
                    rs = pst.executeQuery();
                    while (rs.next()) {

                        String content = "\t|\t" + rs.getString(1) + "\t|\t";
                        System.out.println(content);


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
                                System.out.println("Are you sure you want to purchase this Furniture?");
                                System.out.println("(A) Confirm   (B) Cancel");
                                confirm = st.nextLine();
                                if(confirm.equalsIgnoreCase("A")){
                                    try {


                                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                                        pst = connection.prepareStatement("UPDATE furniture SET status='sold' WHERE furniture_id = '" + FurnitureID + "'");
                                        pst.executeUpdate();

                                        pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                                        pst.setString(1, Sakan.OnlineUser + " has purchased a furniture (" + Sakan.F.getFurnitureDescription() + ")");

                                        pst.executeUpdate();

                                        System.out.println("Furniture purchased successfully!");
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
                                else System.out.println("Invalid input");
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
    System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
    System.out.println("\t      ★★  "+ "Apartment("+floorID+")" +"  ★★      \t");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(A) View pictures of the selected floor            ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(B) View the people living in the selected floor   ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(C) To book the selected floor                     ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(D) Back                                           ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(E) Main menu (Log out)                            ██");
    System.out.println("███████████████████████████████████████████████████████");

    view1  = sc.nextLine();

    if (view1.equalsIgnoreCase("A")) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT picture FROM house_pic  WHERE " + floorID + " = floor_id  ");
            rs = pst.executeQuery();
            while (rs.next()) {

                String content = "\t|\t" + rs.getString(1) + "\t|\t";
                System.out.println(content);


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
                System.out.println(content);


            }
            if(flagpart==0){
                System.out.println("No one is living in this apartment currently");
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
                System.out.println("████████████████████");
                System.out.println("██(A) To go back  ██");
                System.out.println("████████████████████");

                System.out.println("This is a student housing.. Please fill the following data");

                System.out.println("Please select your gender");
                System.out.println("  (M) Male . (F) Female");

                 backb = st.nextLine();
                if (backb.equalsIgnoreCase("A")) {
                    Sakan.whileflag = 1;
                    break;
                } else if (backb.equalsIgnoreCase("M") || backb.equalsIgnoreCase("F")) {
                     Sakan.hp.setPartGender( backb);
                } else continue;

                System.out.println("Please Enter your age: ");
               ageSTR = st.nextLine();
                    if (ageSTR.equalsIgnoreCase("A")) {
                        Sakan.whileflag = 1;
                        break;
                    }

               if(isNumber(ageSTR)){
                   Sakan.hp.setPartAge(Integer.parseInt(ageSTR));
               }
               else continue;

               System.out.println("Please Enter your Major: ");


              Sakan.hp.setPartMajor(st.nextLine());
                    if (Sakan.hp.getPartMajor().equalsIgnoreCase("A")) {
                        Sakan.whileflag = 1;
                        break;
                    }

                    while(true){
                        Sakan.whileflag2=0;
                        System.out.println("Your data is ready.. are you sure you want to continue?");
                        System.out.println("(A) Confirm   (B) Cancel");
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

                              System.out.println("Apartment booked successfully!");

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
                        else System.out.println("Invalid input");
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
                        System.out.println("Your data is ready.. are you sure you want to continue?");
                        System.out.println("(A) Confirm   (B) Cancel");
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

                                System.out.println("Apartment booked successfully!");



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
                        else System.out.println("Invalid input");
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
        }

    }
    public static void addbuildingfunc(int owner_ID){
        Scanner sf=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
       // Sakan.U.setUsersID(0);


        Scanner scon=new Scanner(System.in);


      //  Sakan.F.setFurnitureStatus("forsale");



        System.out.println("███████████████████████████████████████████████████████");
        System.out.println("██(1) Back                                           ██");
        System.out.println("███████████████████████████████████████████████████████");

        System.out.println(" Enter Building_name: ");

        Sakan.B.setBuildingName(sf.nextLine());

        if(Sakan.B.getBuildingName().equalsIgnoreCase("1")){
           // Sakan.flag2 = 1;
           ownerfunc("OWNERS",owner_ID);
        }







        System.out.println(" Enter Location: ");
        Sakan.B.setLocation(sf.nextLine());

        if(Sakan.B.getLocation().equalsIgnoreCase("1")){
           // Sakan.flag2 = 1;
            ownerfunc("OWNERS",owner_ID);
        }



        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT username,CONTACT_NUM FROM USERS WHERE user_id='" + owner_ID + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                Sakan.B.setOwnerName(rs.getString(1));
                Sakan.B.setContactNum(rs.getInt(2));
            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("INSERT INTO BUILDING(OWNER_ID,BUILDING_NAME,LOCATION,FLOORS_NUM,OWNER_NAME,CONTACT_NUM,TOTALPARTICIPANTS) VALUES" + "(?,?,?,?,?,?,?)");

            pst.setInt(1,  owner_ID);
            pst.setString(2, Sakan.B.getBuildingName());
            pst.setString(3, Sakan.B.getLocation());
            pst.setInt(4,0);
            pst.setString(5, Sakan.B.getOwnerName());
            pst.setInt(6, Sakan.B.getContactNum());
            pst.setInt(7, 0);

            pst.executeUpdate();

            pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

            pst.setString(1, Sakan.OnlineUser + " has added a building (" + Sakan.B.getBuildingName() + ") to the system");

            pst.executeUpdate();


            System.out.println("You have successfully added your Building...");


        } catch (SQLException e) {
            e.printStackTrace();

        }

       // Sakan.flag2 = 1;
        ownerfunc("OWNERS",owner_ID);

    }


public static void selectbuilding(int owner_id){
    Scanner sc=new Scanner(System.in);
    Connection connection = null;
    PreparedStatement pst= null;
    ResultSet rs = null;


        System.out.print("Enter the Building ID: ");
        while (true) {
        Sakan.B.setBuildingId(sc.nextInt());

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM building WHERE building_id = '" + Sakan.B.getBuildingId() + "' AND  OWNER_ID = '" + owner_id + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                Sakan.B.setBuildingName(rs.getString(3));
                System.out.println("\t\t\t          ★★★★★★★★   " + Sakan.B.getBuildingName() + "  ★★★★★★★★            \t\t\t");
                String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Location: " + rs.getString(4) + "\t|\t Floors_num: " + rs.getInt(5) + "\t|\t Owner_name: " + rs.getString(6) + "\t|\t Contact_num: " + rs.getInt(7);
                System.out.println(content);
                Sakan.ar.setBuildingName(Sakan.B.getBuildingName());
                Sakan.ar.setOwnerName(rs.getString(6));
                Sakan.ar.setContactNumber(rs.getInt(7));


                viewBuildingFunc(rs.getInt(2), rs.getInt(1));

                //  ownerfunc("OWNERS",rs.getInt(2));
            } else if (!rs.next()) {

                System.out.println("Invalid building ID...");
                break;

            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

}



    public static void viewbuilding(int ownerID){

        Connection connection = null;
        PreparedStatement pst= null;
        PreparedStatement tst= null;
        ResultSet rs = null;
        ResultSet ts = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM building WHERE OWNER_ID= '"+ownerID+"'");

            rs = pst.executeQuery();

            while (rs.next()) {

                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t Building name: " + rs.getString(3)  + "\t|\t Location: " + rs.getString(4) +  "\t|\t FloorsNum: " + rs.getInt(5) + "\t|\t Owner: " + rs.getString(6)  + "\t|\t ContactNumber:" + rs.getInt(7)   + "\t|\t TotalParticipants:" + rs.getInt(8);
                System.out.println(content);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }
        ownerfunc("OWNERS",ownerID);

    }

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

                  selectmyfloor(building_ID);
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

public static void selectmyfloor(int building_id){

    Scanner sc=new Scanner(System.in);
    Connection connection = null;
    PreparedStatement pst= null;
    PreparedStatement tst= null;
    ResultSet rs = null;
    ResultSet ts = null;
    Scanner sf=new Scanner(System.in);
    System.out.print("Enter the floor ID: ");

    Sakan.H.setHouseId(sc.nextInt());

    try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("SELECT * FROM floors WHERE floor_id = '" +  Sakan.H.getHouseId() + "' AND BUILDING_ID= '"+building_id+"'" );
        rs = pst.executeQuery();




        if (rs.next()) {

            String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + "\t|\t";
            System.out.println(content);

            while(true) {
                System.out.println("Do you want to add  pictures for this FLOOR ?");
                System.out.println("A) yes . B) No");
                String ans = sf.next();

                if (ans.equalsIgnoreCase("A")) {
                    System.out.println(" Enter pictures: ");

                    Sakan.hpc.setHousePicture(sf.next());

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
        else if(!rs.next()){
            System.out.println("Please enter a valid floor ID...");

        }




    } catch (SQLException e) {
        e.printStackTrace();

    }





}
    public static void addfloor(int building_id){

        Scanner sf=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst = null;
        PreparedStatement tst = null;
        ResultSet rs = null;
        ResultSet ts = null;



while(true) {
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(111) Back                                         ██");
    System.out.println("███████████████████████████████████████████████████████");

    System.out.println(" Enter Price: ");

    Sakan.H.setHousePrice(sf.nextInt());

    if (Sakan.H.getHousePrice()==111) {
        // Sakan.flag2 = 1;
        break;
    }


    System.out.println(" Enter Services: ");
    Sakan.H.setHouseServices(sf.next());

    if (Sakan.H.getHouseServices().equalsIgnoreCase("111")) {
        // Sakan.flag2 = 1;
        break;
    }

    System.out.println(" How Many Participants Can Live In this Floor? ");
    Sakan.H.setHouseMaxParticipants(sf.nextInt());

    if (Sakan.H.getHouseMaxParticipants()==111) {
        // Sakan.flag2 = 1;
        break;
    }

    System.out.println(" Enter the number of Bedrooms: ");
    Sakan.H.setBedrooms(sf.nextInt());

    if (Sakan.H.getBedrooms()==111) {
        // Sakan.flag2 = 1;
        break;
    }


    System.out.println(" Enter the number of Bathrooms: ");
    Sakan.H.setBathrooms(sf.nextInt());

    if (Sakan.H.getBathrooms()==111) {
        // Sakan.flag2 = 1;
        break;
    }


    System.out.println(" how many Does Balconies does it have? ");
    Sakan.H.setContBalcony(sf.nextInt());

    if (Sakan.H.getContBalcony()==111) {
        // Sakan.flag2 = 1;
        break;
    }


    try {


        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("INSERT INTO FLOORS(BUILDING_ID,AVAILABILITY,PRICE,SERVICES,PARTICIPANTS,MAX_PARTICIPANTS,BEDROOMS,BATHROOMS,BALCONY,STATUS) VALUES" + "(?,?,?,?,?,?,?,?,?,?)");

        pst.setInt(1, building_id);
        pst.setString(2, "available");
        pst.setInt(3, Sakan.H.getHousePrice());
        pst.setString(4, Sakan.H.getHouseServices());
        pst.setInt(5, 0);
        pst.setInt(6, Sakan.H.getHouseMaxParticipants());
        pst.setInt(7, Sakan.H.getBedrooms());
        pst.setInt(8, Sakan.H.getBathrooms());
        pst.setInt(9, Sakan.H.getContBalcony());
        pst.setString(10, "Not_Advertised");

        Sakan.ar.setPrice(Sakan.H.getHousePrice());

        pst.executeUpdate();
        pst = connection.prepareStatement("SELECT floor_id FROM floors ORDER BY floor_id desc limit 1");
        rs = pst.executeQuery();
        if(rs.next()){
            Sakan.ar.setFloorId(rs.getInt(1));
        }

        pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

        pst.setString(1, Sakan.OnlineUser + " has added an apartment  (" + Sakan.B.getBuildingName() + ") to his building");

        pst.executeUpdate();




        System.out.println("You have successfully added the Floor...");
        System.out.println("Waiting for the admin to accept your request");
        pst = connection.prepareStatement("INSERT INTO advertisment_requests (BUILDING_NAME,OWNER_NAME,CONTACT_NUMBER,PRICE,floor_id) VALUES" + "(?,?,?,?,?)");
        pst.setString(1, Sakan.ar.getBuildingName());
        pst.setString(2, Sakan.ar.getOwnerName());
        pst.setInt(3, Sakan.ar.getContactNumber());
        pst.setInt(4, Sakan.ar.getPrice());
        pst.setInt(5, Sakan.ar.getFloorId());
        pst.executeUpdate();
        tst = connection.prepareStatement("UPDATE BUILDING SET FLOORS_NUM= (FLOORS_NUM + 1) WHERE BUILDING_ID='"+building_id+ "'");
        tst.executeUpdate();


    } catch (SQLException e) {
        e.printStackTrace();

    }

    try{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("SELECT FLOOR_ID FROM FLOORS ORDER BY FLOOR_ID DESC LIMIT 1");
        rs= pst.executeQuery();

        if(rs.next()){
            Sakan.H.setHouseId(rs.getInt(1));
        }

    }
    catch (SQLException e) {
        e.printStackTrace();

    }

    System.out.println("Do you want to add  pictures for this FLOOR ?");
    System.out.println("(A) yes . (B) No");
    String ans = sf.next();
//     String picture = null;
    if (ans.equalsIgnoreCase("A")) {
        System.out.println(" Enter pictures: ");

        Sakan.hpc.setHousePicture(sf.next());
        if (  Sakan.hpc.getHousePicture().equalsIgnoreCase("1")) {
            break;
        }
//         picture = pictext;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("INSERT INTO HOUSE_PIC(FLOOR_ID,PICTURE) VALUES (?,?)");
            pst.setInt(1, Sakan.H.getHouseId());
            pst.setString(2, Sakan.hpc.getHousePicture());
            pst.executeUpdate();

            pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

            pst.setString(1, Sakan.OnlineUser + " has added a picture (" +  Sakan.hpc.getHousePicture()+ ") to the apartment");

            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }
        System.out.println("Pictures have been added");
    }else if(ans.equalsIgnoreCase("B")){
        break;
    }





    break;

}

    }




    public static void deleteObservations() throws SQLException {

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
        pst = connection.prepareStatement("DELETE FROM system_observation " );
        pst.executeUpdate();

        System.out.println("You have successfully Deleted the Observations");
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
