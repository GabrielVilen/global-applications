
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

    //private final static String db_host_derby = "jdbc:derby://localhost:1527/ApplicationStationDevelopmentDB";
    private final static String db_host = "jdbc:mysql://localhost:3306/iv1201gasakiproject";
    //private final static String db_host = "jdbc:mysql://mysql.serversfree.com/u570302017_as";
    //private final static String db_host2 = "jdbc:mysql://applicationstation.bug3.com";
    //private final static String db_host3 = "jdbc:mysql://server14.serversfree.com/u570302017_as";
   
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
        //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        System.out.println("driver loaded");
        
        System.out.println("Connecting to database...");
        //System.out.println("DM=" + DriverManager);
        Connection con = DriverManager.getConnection(db_host, "root", "password");
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
