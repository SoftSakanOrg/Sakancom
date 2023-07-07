//import java.util.Scanner;
import java.sql.*;
import java.util.*;
import static java.lang.System.exit;

public class Sakan {

    public static void Mainfunc(){

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


        System.out.println("███████████████████████████████");
        System.out.println("██(1) View available housing ██");
        System.out.println("███████████████████████████████");
        System.out.println("██(2) Main menu              ██");
        System.out.println("███████████████████████████████");

        String view =sc.nextLine();
        if(view.equalsIgnoreCase("1")){


            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst= connection.prepareStatement("SELECT * FROM houses WHERE availability = 'available' ");
                rs = pst.executeQuery();
                while(rs.next()){
                    String content = "\t|\t" + rs.getString(2) + "\t|\t" + rs.getString(3) + "\t|\t" + rs.getInt(4) + "\t|\t" + rs.getString(5) + "\t|\t" + rs.getString(6) + "\t|\t";
                    System.out.println(content);
                }





            }catch (SQLException e) {
                e.printStackTrace();

            }


        }
        else if(view.equalsIgnoreCase("2")){
            Mainfunc();

        }



    }

    public static void Signup(){

        Scanner sc=new Scanner(System.in);
        String name;
        String pass;
        String email;

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        System.out.println("███████████████████████████████");
        System.out.println("██(1) To go back to main menu██");
        System.out.println("███████████████████████████████");

        System.out.print( "Enter your email: " );
        String tempemail =sc.nextLine();
        if(tempemail.equalsIgnoreCase("1")){
            Mainfunc();

        }
           if(!tempemail.contains("@") || !tempemail.contains(".")){
                System.out.println( "Please enter a valid email..." );
                 Signup();
            }

        email = tempemail;

        checkemail(email,1);


        System.out.print( "Enter username: " );
        String tempname =sc.nextLine();
        if(tempname.equalsIgnoreCase("1")){
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

        System.out.println("HERE");


    }

    public static void Login(){




        Scanner sc=new Scanner(System.in);
        String name;
        String pass;
        String email;

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        System.out.println("███████████████████████████████");
        System.out.println("██(1) To go back to main menu██");
        System.out.println("███████████████████████████████");

        System.out.print( "Enter your email: " );
        String tempemail =sc.nextLine();
        if(tempemail.equalsIgnoreCase("1")){
            Mainfunc();

        }
        if(!tempemail.contains("@") || !tempemail.contains(".")){
            System.out.println( "Please enter a valid email..." );
            Login();
        }

        email = tempemail;

        checkemail(email,2);



        System.out.print("Enter password: ");
        String temppass = sc.nextLine();

        if (temppass.equalsIgnoreCase("1")) {
            Mainfunc();

        }

        pass = temppass;


       checklogin(email,pass);





    }

    public static void checklogin(String email, String pass){


        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst = connection.prepareStatement("SELECT EMAIL,PASSWORD FROM TENANTS WHERE EMAIL = '" + email + "' AND PASSWORD = '"+ pass +"'");
            rs = pst.executeQuery();
            while(rs.next()){
                String tempE =  rs.getString(1) ;
                String tempP = rs.getString(3);
                if(!tempE.equals(null) && !tempP.equals(null)){
                    System.out.println("Logged in successfully");
                }
                else {
                    System.out.println("Invalid username or email");
                    Login();
                }
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
            while(rs.next()){
             String tempE =  rs.getString(1);
                if(!tempE.equals(null)){

                    if (func == 1 ) {
                        System.out.println("This email already exists..");
                        Signup();
                    }

                }
                else if(tempE.equals(null) && func ==2){
                    System.out.println("a user with that email doesn't exists..");
                    Login();
                }
            }





        } catch (SQLException e) {
            e.printStackTrace();

        }



    }


    public static void viewhousesfunc(){



    }

    public static void main(String []args){

       Mainfunc();

}


}
