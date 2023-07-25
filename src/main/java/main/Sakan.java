package main;//import java.util.Scanner;
import java.sql.*;
import java.util.*;
import static java.lang.System.exit;
import static java.lang.System.getLogger;

public class Sakan {

    public static int flag1 = 0;
    public static int flag11 = 0;
    public static int flag2 = 0;

    public static int whileflag = 0;
    public static int whileflag2 = 0;

    public static int flagnext = 0;

    public static String OnlineUser = "" ;
    public boolean flaglogin;
    public static Users U=new Users();

    public static Tenant T=new Tenant();

    public static Owners O=new Owners();
    static Floors H=new Floors();
    static Furniture F=new Furniture();
    static houseParticipants hp=new houseParticipants();
    static housePictures hpc=new housePictures();
    static furniturePictures fpc=new furniturePictures();
    public static void Mainfunc(){
           Sakan.flag1 =0;
           Sakan.flag2 = 0;
           Sakan.OnlineUser = "";
           Sakan.flag11 = 0;
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


        if(Sakan.flag2==0){

        System.out.println("█████████████████████████");
        System.out.println("██(1) Sign up          ██");
        System.out.println("█████████████████████████");
        System.out.println("██(2) Log in           ██");
        System.out.println("█████████████████████████");
        System.out.println("██(3) Back to main menu██");
        System.out.println("█████████████████████████");



        temp=sc.nextLine();

        if(temp.equalsIgnoreCase("3")){
            Mainfunc();

        }
        else if(temp.equalsIgnoreCase("1")) {
            Signup(usertype);


        }
        else if(temp.equalsIgnoreCase("2")) {
            Login(usertype);


        }
       }
        Scanner sv = new Scanner(System.in);
        String view;
       while(true) {

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
           System.out.println("██(G) Main menu (Log out)                            ██");
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
           } else if (view.equalsIgnoreCase("G")) {
               Mainfunc();

           }

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
            pst = connection.prepareStatement("SELECT * FROM floors WHERE availability = 'available' ");
            tst = connection.prepareStatement("SELECT B.location,B.building_name FROM floors F,building B WHERE F.building_id = B.building_id ");
            rs = pst.executeQuery();
            ts = tst.executeQuery();
            while (rs.next()) {
                ts.next();
                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t Building name: " + ts.getString(2)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + "\t|\t Location:" + ts.getString(1)  + "\t|\t";
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
        ResultSet rs = null;
        System.out.print("Enter the floor ID: ");

        Sakan.H.setHouseId(sc.nextInt());

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM floors WHERE floor_id = '" +  Sakan.H.getHouseId() + "' AND availability = 'available'" );
            rs = pst.executeQuery();
            if (rs.next()) {
              // Sakan.H.setHouseName(rs.getString(2));
                String content = "\t|\t ID: " + rs.getString(1)  + "\t|\t price: " + rs.getInt(4) +  "\t|\t services: " + rs.getString(5) + "\t|\t Number of residents: " + rs.getString(6)  + "\t|\t";
                System.out.println(content);
                viewfloorsfunc(Sakan.H.getHouseId(),Sakan.OnlineUser);


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
        Sakan.T.setTenantID(0);
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
         pst = connection.prepareStatement("SELECT ID FROM TENANTS WHERE EMAIL='" + Temail + "'");
         rs = pst.executeQuery();

         if (rs.next()) {
             Sakan.T.setTenantID(rs.getInt(1));
         }

         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("INSERT INTO FURNITURE(TENANT_ID,PRICE,DESCRIPTION,STATUS) VALUES" + "(?,?,?,?)");

         pst.setInt(1,  Sakan.T.getTenantID());
         pst.setInt(2, Sakan.F.getFurniturePrice());
         pst.setString(3, Sakan.F.getFurnitureDescription());
         pst.setString(4, Sakan.F.getFurnitureStatus());

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
         // ResultSet rs = null;
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
                  if(usertype.equalsIgnoreCase("OWNERS")){
                      System.out.print("Enter Contact Number: ");
                      Sakan.O.setContactNum(Integer.parseInt(sc.nextLine()));

                      if (Sakan.O.getContactNum()==1) {
                          Mainfunc();

                      }
                  }

              }

         //  pass = temppass;


           try {
               connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
               if (usertype.equalsIgnoreCase("TENANTS")) {
                   pst = connection.prepareStatement("INSERT INTO TENANTS(EMAIL,USERNAME,PASSWORD) VALUES" + "(?,?,?)");

                   pst.setString(1, U.getEmail());
                   pst.setString(2, U.getUsername());
                   pst.setString(3, U.getPassword());
                   pst.executeUpdate();
               }
               if (usertype.equalsIgnoreCase("OWNERS")) {
                   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                   pst = connection.prepareStatement("INSERT INTO OWNERS(EMAIL,USERNAME,PASSWORD,contact_number) VALUES" + "(?,?,?,?)");

                   pst.setString(1, U.getEmail());
                   pst.setString(2, U.getUsername());
                   pst.setString(3, U.getPassword());
                   pst.setInt(4,O.getContactNum());
                   pst.executeUpdate();
               }




                   tst = connection.prepareStatement("INSERT INTO USERS(EMAIL,USERNAME,PASSWORD) VALUES" + "(?,?,?)");

                   tst.setString(1, U.getEmail());
                   tst.setString(2, U.getUsername());
                   tst.setString(3, U.getPassword());

                   tst.executeUpdate();

           System.out.println("Signed  up successfully...");



           } catch (SQLException e) {
               e.printStackTrace();

           }
           Sakan.OnlineUser=U.getEmail();
           Sakan.flag1 = 1;


      }
    }

    public static void Login(String usertype){



        if (Sakan.flag11 == 0) {


            Scanner sc = new Scanner(System.in);



            Connection connection = null;
            PreparedStatement pst = null;
            ResultSet rs = null;




            System.out.println("███████████████████████████████");
            System.out.println("██(1) To go back to main menu██");
            System.out.println("███████████████████████████████");

            System.out.print("Enter your email: ");
            Sakan.T.setEmail(sc.nextLine());
            if ( Sakan.T.getEmail().equalsIgnoreCase("1")) {
                Mainfunc();

            }
            if (!Sakan.T.getEmail().contains("@") || !Sakan.T.getEmail().contains(".")) {
                System.out.println("Please enter a valid email...");
                Login(usertype);
            }

//            email = tempemail;

            checkemail(Sakan.T.getEmail(), 2, usertype);
                if (Sakan.flag11 == 0) {



                System.out.print("Enter password: ");
                    Sakan.T.setPassword(sc.nextLine());

                if (  Sakan.T.getPassword().equalsIgnoreCase("1")) {
                    Mainfunc();

                }

//                pass = temppass;
                    checklogin(T.getEmail(),T.getPassword(),usertype);
            }
                 Sakan.flag11 =1;
            Sakan.OnlineUser =Sakan.U.getEmail() ;

        }






    }

    public static void checklogin(String email, String pass, String usertype){


        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT EMAIL,PASSWORD FROM TENANTS WHERE EMAIL = '" + email + "' AND PASSWORD = '"+ pass +"'");
            rs = pst.executeQuery();
            if(rs.next()){

                String tempE =  rs.getString(1) ;
                String tempP = rs.getString(2);
                if(!tempE.equals(null) && !tempP.equals(null)){
                    System.out.println("Logged in successfully");
                    Sakan.flag1 = 1;
                }

            }
            else if(!rs.next()) {
                System.out.println("Invalid username or email");
                Login(usertype);
            }





        } catch (SQLException e) {
            e.printStackTrace();

        }



    }

    public static void checkemail(String email, int func, String usertype ){  //func 1 forSign up // func2 for Login


        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
             pst = connection.prepareStatement("SELECT EMAIL FROM USERS WHERE EMAIL = '" + email + "' "  );
             rs = pst.executeQuery();

            if(rs.next()){

             String tempE =  rs.getString(1);

                if(Sakan.flag1==0) {

                if(!tempE.equals(null)){

                       if (func == 1) {
                           System.out.println("This email already exists..");
                           Signup(usertype);
                       }
                   }


             }
            }
            else if(!rs.next()){
                if (flag11==0) {
                    if (func == 2) {
                        System.out.println("a user with that email doesn't exists..");
                        Login(usertype);
                    }
                }
            }






        } catch (SQLException e) {
            e.printStackTrace();

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



    public static void viewfloorsfunc( int floorID, String Temail) {
        Scanner st = new Scanner(System.in);
        Scanner sc=new Scanner(System.in);

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        String view1;
while(true) {
    System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
    System.out.println("\t      ★★  "+ "Apartment("+floorID+")" +"  ★★      \t");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(A) View pictures of the selected floor            ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(B) View the people living in the selected floor   ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(C) To book the selected floor                    ██");
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
        String hosid = null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
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
                                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                                pst = connection.prepareStatement("SELECT username FROM tenants WHERE email = '" + Temail + "'" );
                                rs = pst.executeQuery();

                                if(rs.next()){
                                     Sakan.T.setUsername(rs.getString(1));

                                }
                                pst = connection.prepareStatement("INSERT INTO house_participants(floor_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,floorID );
                                pst.setString(2,Sakan.T.getUsername() );
                                pst.setInt(3, Sakan.hp.getPartAge());
                                pst.setString(4, Sakan.hp.getPartMajor());
                                pst.setString(5, Sakan.hp.getPartGender() );

                                pst.executeUpdate();
                                pst = connection.prepareStatement("UPDATE floors SET participants = (participants+1) WHERE floor_id = '" + floorID + "'");
                                pst.executeUpdate();
                                pst = connection.prepareStatement("SELECT floor_id FROM floors WHERE participants = max_participants ");
                                rs = pst.executeQuery();

                                if(rs.next()){

                                    pst = connection.prepareStatement("UPDATE floors SET availability = 'unavailable' WHERE floor_id = '" + floorID + "'");
                                    pst.executeUpdate();
                                }

                              System.out.println("Apartment booked successfully!");
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
                                pst = connection.prepareStatement("SELECT username FROM tenants WHERE email = '" + Temail + "'" );
                                rs = pst.executeQuery();

                                if(rs.next()){
                                    nameb = rs.getString(1);

                                }
                                pst = connection.prepareStatement("INSERT INTO house_participants(floor_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,floorID );
                                pst.setString(2,nameb );
                                pst.setInt(3, ageb);
                                pst.setString(4, majorb);
                                pst.setString(5, gender );

                                pst.executeUpdate();
                                pst = connection.prepareStatement("UPDATE floors SET participants = (participants+1) WHERE floor_id = '" + floorID + "'");
                                pst.executeUpdate();
                                pst = connection.prepareStatement("SELECT floor_id FROM floors WHERE participants = max_participants ");
                                rs = pst.executeQuery();

                                if(rs.next()){

                                    pst = connection.prepareStatement("UPDATE floors SET availability = 'unavailable' WHERE floor_id = '" + floorID + "'");
                                    pst.executeUpdate();
                                }

                                System.out.println("Apartment booked successfully!");
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

    public static void ownerfunc(String usertype){



        Scanner sc=new Scanner(System.in);

        String temp;


        if(Sakan.flag2==0){

            System.out.println("█████████████████████████");
            System.out.println("██(1) Sign up          ██");
            System.out.println("█████████████████████████");
            System.out.println("██(2) Log in           ██");
            System.out.println("█████████████████████████");
            System.out.println("██(3) Back to main menu██");
            System.out.println("█████████████████████████");



            temp=sc.nextLine();

            if(temp.equalsIgnoreCase("3")){
                Mainfunc();

            }
            else if(temp.equalsIgnoreCase("1")) {
                Signup(usertype);


            }
            else if(temp.equalsIgnoreCase("2")) {
                Login(usertype);


            }
        }
        Scanner sv = new Scanner(System.in);
        String view;
        while(true) {

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
            System.out.println("██(G) Main menu (Log out)                            ██");
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
            } else if (view.equalsIgnoreCase("G")) {
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