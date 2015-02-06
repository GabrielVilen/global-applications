package se.kth.iv1201projekt.integration.test_temp;


import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kborak
 */
public class TestDBConnection {

    private final static String db_host = "jdbc:mysql://localhost:3306/iv1201gasakiproject";
    private final static String db_user = "root";
    private final static String db_pass = "password";

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
        Connection con = DriverManager.getConnection(db_host, "root", "password");
        System.out.println("con=" + con);
    }

    public static boolean isInternetReachable() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            Object objData = urlConnect.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
