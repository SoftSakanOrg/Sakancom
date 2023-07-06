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

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        System.out.println("█████████████████████████");
        System.out.println("██(1) Back to main menu██");
        System.out.println("█████████████████████████");
        System.out.print( "Enter username: " );

        temp=sc.nextLine();

        if(temp.equalsIgnoreCase("1")){
            Mainfunc();

        }

        name=temp;

        System.out.print( "Enter password: " );
        pass=sc.nextLine();

        if(pass.equalsIgnoreCase("1")){
            Mainfunc();

        }


        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst= connection.prepareStatement("INSERT INTO TENANTS(USERNAME,PASSWORD) VALUES"+"(?,?)");


            pst.setString(1, name);
            pst.setString(2, pass);

            pst.executeUpdate();




        }catch (SQLException e) {
            e.printStackTrace();

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
                   String content = "|  " + rs.getString(2) + "  |  " + rs.getString(3) + "  |  " + rs.getInt(4) + "  |  " + rs.getString(5) + "  |  " + rs.getString(6) + "  |"  ;
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
    public static void viewhousesfunc(){



    }

    public static void main(String []args){

       Mainfunc();

}


}
