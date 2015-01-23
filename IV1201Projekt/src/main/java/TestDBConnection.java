
import java.awt.BorderLayout;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kborak
 */
public class TestDBConnection {

    private final static String db_host = "http://mysql.serversfree.com/u570302017_as";
    private final static String db_user = "u570302017_admin";
    private final static String db_pass = "iv1201";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.print("testing network... ");
        if (!isInternetReachable()) {
            System.out.println("FAILED! Exiting.");
        }
        System.out.println("SUCCESS!");
        System.out.println("loading driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("driver loaded");
        System.out.println("Connecting to database...");
        Connection con = DriverManager.getConnection(db_host, db_user, db_pass);
        System.out.println("con=" + con);

    }

    public static boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

                //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
