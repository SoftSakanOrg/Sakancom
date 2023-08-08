package main;

import coderunner.Sakan;

import java.sql.*;

import static coderunner.Sakan.logger;

@SuppressWarnings("java:S1118")
public class FuncAddFloor {

    private static int addFloorFlag;
    private static int selectLastFloorFlag;
    private static int observationFlag;
    private static int requestFlag;
    private static int floorNumFlag;


    public FuncAddFloor() {

        /***
        do nothing
         ***/
    }

    public static int getAddFloorFlag() {
        return addFloorFlag;
    }

    public static void setAddFloorFlag(int addFloorFlag) {
        FuncAddFloor.addFloorFlag = addFloorFlag;
    }

    public static int getSelectLastFloorFlag() {
        return selectLastFloorFlag;
    }

    public static void setSelectLastFloorFlag(int selectLastFloorFlag) {
        FuncAddFloor.selectLastFloorFlag = selectLastFloorFlag;
    }

    public static int getObservationFlag() {
        return observationFlag;
    }

    public static void setObservationFlag(int observationFlag) {
        FuncAddFloor.observationFlag = observationFlag;
    }

    public static int getRequestFlag() {
        return requestFlag;
    }

    public static void setRequestFlag(int requestFlag) {
        FuncAddFloor.requestFlag = requestFlag;
    }

    public static int getFloorNumFlag() {
        return floorNumFlag;
    }

    public static void setFloorNumFlag(int floorNumFlag) {
        FuncAddFloor.floorNumFlag = floorNumFlag;
    }

    public static void addfloor(int buildingId, int testp) throws SQLException {


        
        Connection connection = null;
        PreparedStatement pst = null;
        PreparedStatement tst = null;
        ResultSet rs = null;









         setAddFloorFlag(1);
                
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst = connection.prepareStatement("INSERT INTO FLOORS(BUILDING_ID,AVAILABILITY,PRICE,SERVICES,PARTICIPANTS,MAX_PARTICIPANTS,BEDROOMS,BATHROOMS,BALCONY,STATUS) VALUES" + "(?,?,?,?,?,?,?,?,?,?)");
        if(testp==1) {
                pst.setInt(1, buildingId);
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

                    setSelectLastFloorFlag(1);

                    Sakan.ar.setFloorId(rs.getInt(1));
                }

                pst = connection.prepareStatement("INSERT INTO system_observation(DESCRIPTION) VALUES (?)");

                pst.setString(1, Sakan.onlineUser + " has added an apartment  (" + Sakan.b.getBuildingName() + ") to his building");

                setObservationFlag(1);

                if(testp==1) {
                    pst.executeUpdate();

                }


                setAddFloorFlag(1);

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
                setAddFloorFlag(1);
                tst = connection.prepareStatement("UPDATE BUILDING SET FLOORS_NUM= (FLOORS_NUM + 1) WHERE BUILDING_ID='"+buildingId+ "'");
                floorNumFlag=1;
        if(testp==1) {
            tst.executeUpdate();
        }

           

         
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
                pst = connection.prepareStatement("SELECT FLOOR_ID FROM FLOORS ORDER BY FLOOR_ID DESC LIMIT 1");
                rs= pst.executeQuery();

                if(rs.next()){
                   setSelectLastFloorFlag(1);
                    Sakan.h.setHouseId(rs.getInt(1));
                }

           
          









    }













}
