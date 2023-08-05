package main;

import java.sql.*;

import static main.Sakan.logger;

public class funcAddFloor {

    public static int addFloorFlag;
    public static int selectLastFloorFlag;
    public static int observationFlag;
    public static int requestFlag;
    public static int floorNumFlag;
    public static void addfloor(int building_id,int testp) throws SQLException {


        
        Connection connection = null;
        PreparedStatement pst = null;
        PreparedStatement tst = null;
        ResultSet rs = null;
        ResultSet ts = null;






     

                addFloorFlag=1;
                
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst = connection.prepareStatement("INSERT INTO FLOORS(BUILDING_ID,AVAILABILITY,PRICE,SERVICES,PARTICIPANTS,MAX_PARTICIPANTS,BEDROOMS,BATHROOMS,BALCONY,STATUS) VALUES" + "(?,?,?,?,?,?,?,?,?,?)");
        if(testp==1) {
                pst.setInt(1, building_id);
                pst.setString(2, "available");
                pst.setInt(3, Sakan.h.getHousePrice());
                pst.setString(4, Sakan.h.getHouseServices());
                pst.setInt(5, 0);
                pst.setInt(6, Sakan.h.getHouseMaxParticipants());
                pst.setInt(7, Sakan.h.getBedrooms());
                pst.setInt(8, Sakan.h.getBathrooms());
                pst.setInt(9, Sakan.h.getContBalcony());
                pst.setString(10, "Not_Advertised");

                Sakan.ar.setPrice(Sakan.h.getHousePrice());

                pst.executeUpdate();
            }
        logger.info("You have successfully added the Floor...");
        logger.info("Waiting for the admin to accept your request");

                pst = connection.prepareStatement("SELECT floor_id FROM floors ORDER BY floor_id desc limit 1");
                rs = pst.executeQuery();



                if(rs.next()){
                    selectLastFloorFlag=1;
                    Sakan.ar.setFloorId(rs.getInt(1));
                }

                pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                pst.setString(1, Sakan.onlineUser + " has added an apartment  (" + Sakan.b.getBuildingName() + ") to his building");
                observationFlag=1;
                if(testp==1) {
                    pst.executeUpdate();

                }


                addFloorFlag=1;
                requestFlag=1;
                pst = connection.prepareStatement("INSERT INTO advertisment_requests (BUILDING_NAME,OWNER_NAME,CONTACT_NUMBER,PRICE,floor_id) VALUES" + "(?,?,?,?,?)");
                pst.setString(1, Sakan.ar.getBuildingName());
                pst.setString(2, Sakan.ar.getOwnerName());
                pst.setInt(3, Sakan.ar.getContactNumber());
                pst.setInt(4, Sakan.ar.getPrice());
                pst.setInt(5, Sakan.ar.getFloorId());
                if(testp==1) {
                    pst.executeUpdate();
                }
                addFloorFlag=1;
                tst = connection.prepareStatement("UPDATE BUILDING SET FLOORS_NUM= (FLOORS_NUM + 1) WHERE BUILDING_ID='"+building_id+ "'");
                floorNumFlag=1;
        if(testp==1) {
            tst.executeUpdate();
        }

           

         
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst = connection.prepareStatement("SELECT FLOOR_ID FROM FLOORS ORDER BY FLOOR_ID DESC LIMIT 1");
                rs= pst.executeQuery();

                if(rs.next()){
                   selectLastFloorFlag=1;
                    Sakan.h.setHouseId(rs.getInt(1));
                }

           
          









    }













}
