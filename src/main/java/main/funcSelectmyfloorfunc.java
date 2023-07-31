package main;

import java.sql.*;
import java.util.Scanner;

public class funcSelectmyfloorfunc {

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


}
