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
            System.out.println( "hello" );
            System.out.println( "1) Tenant." );
            System.out.println( "2) Owner." );
            System.out.println( "3) Admin." );
            System.out.print( "Choose between the specific users :  " );
            c= sc.nextLine();



            if(c.equalsIgnoreCase("Tenant")){
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
        ResultSet resultSet = null;
        String arr[]={"home1","home2","home3"};

        System.out.print( "username: " );

        temp=sc.nextLine();

        if(temp.equalsIgnoreCase("back")){
            Mainfunc();

        }

        name=temp;

        System.out.print( "password: " );
        pass=sc.nextLine();


        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
            pst= connection.prepareStatement("INSERT INTO TENANTS(USERNAME,PASSWORD) VALUES"+"(?,?)");


            pst.setString(1, name);
            pst.setString(2, pass);

            pst.executeUpdate();




        }catch (SQLException e) {
            e.printStackTrace();

        }

        System.out.println("Available houses: ");

        for(int i=0;i<3;i++){

            System.out.print(arr[i]+"\t");


        }
        System.out.print("\n");
        System.out.print("\n");

       Mainfunc();

    }
    public static void hello(){

        System.out.println("Hello world");

    }

    public static void main(String []args){

       Mainfunc();

}


}
