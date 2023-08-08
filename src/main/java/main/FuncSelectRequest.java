package main;

import coderunner.Sakan;

import java.sql.*;


import static coderunner.Sakan.logger;
@SuppressWarnings("java:S1118")
public class FuncSelectRequest {

    private static int requestflag =0;

    public static int getRequestflag() {
        return requestflag;
    }

    public static void setRequestflag(int requestflag) {
        FuncSelectRequest.requestflag = requestflag;
    }

    public static void selectRequest(int reqId) throws SQLException {
        setRequestflag(0);
        Connection connection = null;
        PreparedStatement pst= null;
        ResultSet rs = null;
        Sakan.flagSelectRequest = 0;


            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");


                pst = connection.prepareStatement("SELECT * FROM advertisment_requests WHERE req_id = '" + reqId + "'");
                rs = pst.executeQuery();

                if (!rs.next()) {
                    logger.info("Please enter a valid request ID...");
                    setRequestflag(0);
                }

                rs = pst.executeQuery();
                if (rs.next()) {
                    setRequestflag(1);
                    Sakan.flagSelectRequest = 1;
                    String content = "\t|\t ID: " + rs.getInt(1) + "\t|\t Building_Name: " + rs.getString(2) + "\t|\t Owner_name: " + rs.getString(3) + "\t|\t Contact_Number: " + rs.getInt(4) + "\t|\t" + rs.getInt(5) + "\t|\t Floor_ID: " + rs.getInt(6) + "\t|\t";
                    logger.info(content);
                    Sakan.ar.setReqId(rs.getInt(1));
                }










    }
}
