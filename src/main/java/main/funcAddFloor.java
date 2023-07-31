package main;

import java.sql.*;
import java.util.Scanner;

public class funcAddFloor {

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













}
