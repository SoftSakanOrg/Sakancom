//import java.util.Scanner;
import java.sql.*;
import java.util.*;
import static java.lang.System.exit;

public class Sakan {
    public static int flag1 = 0;
    public static int flag11 = 0;
    public static int flag2 = 0;

    public static int whileflag = 0;
    public static int whileflag2 = 0;

    public static int flagnext = 0;

    public static String OnlineUser = "" ;
    public static void Mainfunc(){
           Sakan.flag1 =0;
           Sakan.flag2 = 0;
           Sakan.OnlineUser = "";
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
                tenantfunc();


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

    public static void tenantfunc(){



        Scanner sc=new Scanner(System.in);
        String c ;
        String name;
        String pass;
        String temp;
        String email;

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
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
            Signup();


        }
        else if(temp.equalsIgnoreCase("2")) {
            Login();


        }
       }
        Scanner sv = new Scanner(System.in);
        String view;
       while(true) {

           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(A) View available housing                         ██");
           System.out.println("███████████████████████████████████████████████████████");
           System.out.println("██(B) To Select one of the available houses using ID ██");
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

             viewhouse();

           } else if (view.equalsIgnoreCase("B")) {
               selecthouse();
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

    public static void viewhouse(){
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM houses WHERE availability = 'available' ");
            rs = pst.executeQuery();
            while (rs.next()) {
                String content = "\t|\t ID: " + rs.getString(1) + " \t|\t HouseName: " + rs.getString(2) + "\t|\t availability: " + rs.getString(3) + "\t|\t price: " + rs.getInt(4) + "\t|\t location: " + rs.getString(5) + "\t|\t services: " + rs.getString(6) + "\t|\t Number of residents: " + rs.getString(7) + "\t|\t Owner Name: " + rs.getString(9) + "\t|\t Contact number: " + rs.getInt(10) + "\t|\t";
                System.out.println(content);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void selecthouse(){
        Scanner sc=new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        System.out.print("Enter the house ID: ");

        int houseNum =sc.nextInt();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM houses WHERE house_id = '" + houseNum + "' AND availability = 'available'" );
            rs = pst.executeQuery();
            if (rs.next()) {
                String Housename = rs.getString(2);
                String content = "\t|\t ID: " + rs.getString(1) + " \t|\t HouseName: " + rs.getString(2) + "\t|\t availability: " + rs.getString(3) + "\t|\t price: " + rs.getInt(4) + "\t|\t location: " + rs.getString(5) + "\t|\t services: " + rs.getString(6) + "\t|\t Number of residents: " + rs.getString(7) + "\t|\t Owner Name: " + rs.getString(9) + "\t|\t Contact number: " + rs.getInt(10) + "\t|\t";
                System.out.println(content);
                viewhousesfunc(Housename,houseNum,Sakan.OnlineUser);


            }
            else if(!rs.next()){
                System.out.println("Please enter a valid house ID...");

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

        int furnitureNum =sc.nextInt();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT * FROM furniture WHERE furniture_id = '" + furnitureNum + "' AND status = 'forsale'" );
            rs = pst.executeQuery();
            if (rs.next()) {
                String fdescription = rs.getString(4);
                String content = "\t|\t ID: " + rs.getInt(1) + " \t|\t price: " + rs.getInt(3) + "\t|\t description: " + rs.getString(4) + "\t|\t";
                System.out.println(content);
                viewfurnituresfunc(fdescription,furnitureNum);


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
        int TenantID=0;
        int furID=0;
        Scanner scon=new Scanner(System.in);
        int furIDVal=0;

        String fstatus="forsale";

//        try{
//                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
//                pst = connection.prepareStatement("SELECT ID FROM TENANTS WHERE EMAIL='"+Temail+"'");
//                rs=pst.executeQuery();
//
//                if(rs.next()){
//                    TenantID=rs.getInt(1);
//                }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//
//        }

        System.out.println("███████████████████████████████████████████████████████");
        System.out.println("██(1) Back                                           ██");
        System.out.println("███████████████████████████████████████████████████████");

        System.out.println(" Enter description: ");
        String description;
        String text=sf.nextLine();

       if(text.equalsIgnoreCase("1")){
           Sakan.flag2 = 1;
           tenantfunc();
       }
         description=text;



        int price;

        System.out.println(" Enter price: ");
        int tnum=sf.nextInt();

        if(String.valueOf(tnum).equalsIgnoreCase("1")){
            Sakan.flag2 = 1;
            tenantfunc();
        }
        price=tnum;

     try {

         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("SELECT ID FROM TENANTS WHERE EMAIL='" + Temail + "'");
         rs = pst.executeQuery();

         if (rs.next()) {
             TenantID = rs.getInt(1);
         }

         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("INSERT INTO FURNITURE(TENANT_ID,PRICE,DESCRIPTION,STATUS) VALUES" + "(?,?,?,?)");

         pst.setInt(1, TenantID);
         pst.setInt(2, price);
         pst.setString(3, description);
         pst.setString(4, fstatus);

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
          furIDVal=rs.getInt(1);
         }

     }
     catch (SQLException e) {
         e.printStackTrace();

     }
     try {
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
         pst = connection.prepareStatement("SELECT FURNITURE_ID FROM FURNITURE WHERE FURNITURE_ID='" + furIDVal + "'");
         rs = pst.executeQuery();
         if (rs.next()) {
             furID = rs.getInt(1);
         }
     } catch (SQLException e) {
         e.printStackTrace();

     }
     System.out.println("Do you want to add  pictures for this furniture?");
     System.out.println("A) yes . B) No");
     String ans = scon.nextLine();
     String picture = null;
     if (ans.equalsIgnoreCase("A")) {
         System.out.println(" Enter pictures: ");

         String pictext = scon.nextLine();
         if (pictext.equalsIgnoreCase("1")) {
             Sakan.flag2 = 1;
             tenantfunc();
         }
         picture = pictext;
         try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
             pst = connection.prepareStatement("INSERT INTO FURNITURE_PIC(FURNITURE_ID,PICTURE) VALUES (?,?)");
             pst.setInt(1, furID);
             pst.setString(2, picture);
             pst.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();

         }
         System.out.println("Pictures have been added");
     }


     Sakan.flag2 = 1;
     tenantfunc();

    }

    public static void Signup(){
      if (Sakan.flag1 == 0) {
          Scanner sc = new Scanner(System.in);
          String name;
          String pass;
          String email;

          Connection connection = null;
          PreparedStatement pst = null;
          ResultSet rs = null;


          System.out.println("███████████████████████████████");
          System.out.println("██(1) To go back to main menu██");
          System.out.println("███████████████████████████████");

          System.out.print("Enter your email: ");
          String tempemail = sc.nextLine();
          if (tempemail.equalsIgnoreCase("1")) {
              Mainfunc();

          }
          if (!tempemail.contains("@") || !tempemail.contains(".")) {
              System.out.println("Please enter a valid email...");
              Signup();
          }

          email = tempemail;

          checkemail(email, 1);
              if(Sakan.flag1==0) {

           System.out.print("Enter username: ");
           String tempname = sc.nextLine();
           if (tempname.equalsIgnoreCase("1")) {
               Mainfunc();

           }
           name = tempname;


           System.out.print("Enter password: ");
           String temppass = sc.nextLine();

           if (temppass.equalsIgnoreCase("1")) {
               Mainfunc();

           }

           pass = temppass;


           try {
               connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
               pst = connection.prepareStatement("INSERT INTO TENANTS(EMAIL,USERNAME,PASSWORD) VALUES" + "(?,?,?)");

               pst.setString(1, email);
               pst.setString(2, name);
               pst.setString(3, pass);

               pst.executeUpdate();


           } catch (SQLException e) {
               e.printStackTrace();

           }
           Sakan.OnlineUser=email;
           Sakan.flag1 = 1;

       }
      }
    }

    public static void Login(){

        if (Sakan.flag11 == 0) {


            Scanner sc = new Scanner(System.in);
            String name;
            String pass;
            String email;

            Connection connection = null;
            PreparedStatement pst = null;
            ResultSet rs = null;


            System.out.println("███████████████████████████████");
            System.out.println("██(1) To go back to main menu██");
            System.out.println("███████████████████████████████");

            System.out.print("Enter your email: ");
            String tempemail = sc.nextLine();
            if (tempemail.equalsIgnoreCase("1")) {
                Mainfunc();

            }
            if (!tempemail.contains("@") || !tempemail.contains(".")) {
                System.out.println("Please enter a valid email...");
                Login();
            }

            email = tempemail;

            checkemail(email, 2);
                if (Sakan.flag11 == 0) {



                System.out.print("Enter password: ");
                String temppass = sc.nextLine();

                if (temppass.equalsIgnoreCase("1")) {
                    Mainfunc();

                }

                pass = temppass;
                checklogin(email,pass);
            }
                 Sakan.flag11 =1;
            Sakan.OnlineUser =email ;

        }






    }

    public static void checklogin(String email, String pass){


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
                Login();
            }





        } catch (SQLException e) {
            e.printStackTrace();

        }



    }

    public static void checkemail(String email, int func ){  //func 1 forSign up // func2 for Login


        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
             pst = connection.prepareStatement("SELECT EMAIL FROM TENANTS WHERE EMAIL = '" + email + "'" );
             rs = pst.executeQuery();

            if(rs.next()){

             String tempE =  rs.getString(1);

                if(Sakan.flag1==0) {

                if(!tempE.equals(null)){

                       if (func == 1) {
                           System.out.println("This email already exists..");
                           Signup();
                       }
                   }


             }
            }
            else if(!rs.next()){
                if (flag11==0) {
                    if (func == 2) {
                        System.out.println("a user with that email doesn't exists..");
                        Login();
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
                                        tenantfunc();

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
                tenantfunc();
            }

            else if (view1.equalsIgnoreCase("D")) {
                Mainfunc();

            }


            }


        }



    public static void viewhousesfunc(String HouseName , int HouseID, String Temail) {
        Scanner st = new Scanner(System.in);
        Scanner sc=new Scanner(System.in);

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        String view1;
while(true) {
    System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
    System.out.println("\t            ★★  " + HouseName + "  ★★                 \t");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(A) View pictures of the selected house            ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(B) View the people living in the selected house   ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(C) To book the selected house                     ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(D) Back                                           ██");
    System.out.println("███████████████████████████████████████████████████████");
    System.out.println("██(E) Main menu (Log out)                            ██");
    System.out.println("███████████████████████████████████████████████████████");

    view1  = sc.nextLine();

    if (view1.equalsIgnoreCase("A")) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT picture FROM house_pic  WHERE " + HouseID + " = house_id  ");
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
            pst = connection.prepareStatement("SELECT * FROM house_participants  WHERE " + HouseID + " = house_id  ");
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
            pst = connection.prepareStatement("SELECT * FROM houses  WHERE " + HouseID + " = house_id AND max_participants > 1 ");
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
                     gender = backb;
                } else continue;

                System.out.println("Please Enter your age: ");
               ageSTR = st.nextLine();
                    if (ageSTR.equalsIgnoreCase("A")) {
                        Sakan.whileflag = 1;
                        break;
                    }

               if(isNumber(ageSTR)){
                   ageb = Integer.parseInt(ageSTR);
               }
               else continue;

               System.out.println("Please Enter your Major: ");


               majorb = st.nextLine();
                    if (majorb.equalsIgnoreCase("A")) {
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
                                     nameb = rs.getString(1);

                                }
                                pst = connection.prepareStatement("INSERT INTO house_participants(house_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,HouseID );
                                pst.setString(2,nameb );
                                pst.setInt(3, ageb);
                                pst.setString(4, majorb);
                                pst.setString(5, gender );

                                pst.executeUpdate();
                                pst = connection.prepareStatement("UPDATE houses SET participants = (participants+1) WHERE house_id = '" + HouseID + "'");
                                pst.executeUpdate();
                                pst = connection.prepareStatement("SELECT house_id FROM houses WHERE participants = max_participants ");
                                rs = pst.executeQuery();

                                if(rs.next()){

                                    pst = connection.prepareStatement("UPDATE houses SET availability = 'unavailable' WHERE house_id = '" + HouseID + "'");
                                    pst.executeUpdate();
                                }

                              System.out.println("Apartment booked successfully!");
                                Sakan.whileflag2 = 1;
                                Sakan.flag2 = 1;
                                tenantfunc();
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
                                pst = connection.prepareStatement("INSERT INTO house_participants(house_id,part_name,part_age,part_major,part_gender) VALUES" + "(?,?,?,?,?)");
                                pst.setInt(1,HouseID );
                                pst.setString(2,nameb );
                                pst.setInt(3, ageb);
                                pst.setString(4, majorb);
                                pst.setString(5, gender );

                                pst.executeUpdate();
                                pst = connection.prepareStatement("UPDATE houses SET participants = (participants+1) WHERE house_id = '" + HouseID + "'");
                                pst.executeUpdate();
                                pst = connection.prepareStatement("SELECT house_id FROM houses WHERE participants = max_participants ");
                                rs = pst.executeQuery();

                                if(rs.next()){

                                    pst = connection.prepareStatement("UPDATE houses SET availability = 'unavailable' WHERE house_id = '" + HouseID + "'");
                                    pst.executeUpdate();
                                }

                                System.out.println("Apartment booked successfully!");
                                Sakan.flag2 = 1;
                                Sakan.whileflag2 = 1;
                                tenantfunc();

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
        tenantfunc();
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
