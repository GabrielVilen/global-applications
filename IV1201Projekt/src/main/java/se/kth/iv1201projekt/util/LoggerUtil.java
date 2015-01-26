package se.kth.iv1201projekt.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for logging exceptions.
 * @author Samy
 */
public class LoggerUtil {

    
    
   public static void logSevere(Exception e, Object obj){
       Logger logger = Logger.getLogger(obj.getClass().getName());
       logger.log(Level.SEVERE,"Severe",e);
   }
   
   public static void logMinor(Exception e, Object obj){
       Logger logger = Logger.getLogger(obj.getClass().getName());
       logger.log(Level.FINE, "Minor", e);
   }
   
   public static void logMethodUsage(){
       
   }
   
   public static Handler getHandler(){
       Handler handler=null;
       try {
           handler = new FileHandler("../logs/logExceptions.txt", 1024,10);
       } catch (IOException ex) {
           ex.printStackTrace();
       } catch (SecurityException ex) {
           ex.printStackTrace();
       }
       return handler;
   }
   
}
 