//import java.util.Scanner;
import java.sql.*;
import java.util.*;
import static java.lang.System.exit;

public class Sakan {

    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        String c ;
        String name;
        String pass;

        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet resultSet = null;
        String arr[]={"home1","home2","home3"};
        while (true)
        {

            System.out.println( "1) Tolerant." );
            System.out.println( "2) Owner." );
            System.out.println( "3) Admin." );
            System.out.print( "Choose between the specific users :  " );
            c= sc.nextLine();

            if(c.equals("Tolerant")||c.equals("tolerant")){
                System.out.print( "username: " );
                 name=sc.nextLine();

                System.out.print( "password: " );
                pass=sc.nextLine();

                System.out.println("The name is "+name+" The pass is "+pass);
                   try{
                       connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                       pst= connection.prepareStatement("INSERT INTO TOLERANTS (USERNAME,PASSWORD) VALUES"+"(?,?)");


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



            }
            else if(c.equals("exit")){
                exit(0);
            }
            else{
                System.out.println("Please make sure to enter the right user........");
                System.out.print("\n");

            }


        }
}
}
