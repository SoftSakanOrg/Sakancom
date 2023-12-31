package main;

import coderunner.Sakan;

import java.sql.*;

import static coderunner.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncRequestAction {

    public static final String JDBC_MYSQL_LOCALHOST_3306_SAKAN = "jdbc:mysql://localhost:3306/Sakan";
    private static int requestflag =0;


    public static int getRequestflag() {
        return requestflag;
    }

    public static void setRequestflag(int requestflag) {
        FuncRequestAction.requestflag = requestflag;
    }

    public static void requestAction(int id, String answer, int testp) throws SQLException {

        requestflag =0;



        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;


         Sakan.flagRequestAction=0;


            connection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_SAKAN, "root", "");
            pst = connection.prepareStatement("SELECT FLOOR_ID FROM advertisment_requests WHERE REQ_ID='"+id+"'");
            rs= pst.executeQuery();

            if(rs.next()){
                setRequestflag(1);
                Sakan.ar.setFloorId(rs.getInt(1));
            }



            if (answer.equalsIgnoreCase("A")) {

                setRequestflag(1);


                acceptedextracted(id, testp);


                logger.info("Request has been Approved...");

            }else if(answer.equalsIgnoreCase("B")){

                setRequestflag(1);


                rejectedextracted(id, testp);


                logger.info("Request has been Rejected...");

            }

            else if ( answer.equalsIgnoreCase("C")) {

                setRequestflag(1);

            }
            else {
                Sakan.flagRequestAction=1;

            }

    }




    private static void acceptedextracted(int id, int testp) throws SQLException {
        Connection connection;
        PreparedStatement pst ;



        connection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_SAKAN, "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("UPDATE FLOORS SET STATUS='Advertised' where FLOOR_ID='" + Sakan.ar.getFloorId() + "'");
            pst.executeUpdate();
        }
        connection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_SAKAN, "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("DELETE FROM advertisment_requests where REQ_ID='" + id + "'");
            pst.executeUpdate();
        }
    }


    private static void rejectedextracted(int id, int testp) throws SQLException {
        PreparedStatement pst;
        Connection connection;
        connection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_SAKAN, "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("DELETE FROM advertisment_requests where REQ_ID='" + id + "'");
            pst.executeUpdate();
        }

        connection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_SAKAN, "root", "");
        if(testp ==1) {
            pst = connection.prepareStatement("DELETE FROM FLOORS where FLOOR_ID='" + Sakan.ar.getFloorId() + "'");
            pst.executeUpdate();
        }
    }

}
