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

    private static final String exceptionFile = "../logs/exceptionLog.txt";
    private static final String methodFile ="../logs/methodLog.txt";
    
   public static void logSevere(Exception e, Object obj){
       Logger logger = Logger.getLogger(obj.getClass().getName());
       logger.addHandler(getHandler(exceptionFile));
       logger.log(Level.SEVERE,"Severe",e);
   }
   
   public static void logMinor(Exception e, Object obj){
       Logger logger = Logger.getLogger(obj.getClass().getName());
       logger.addHandler(getHandler(exceptionFile));
       logger.log(Level.FINE, "Minor", e);
   }
   
   public static void logMethod(Object obj){
       Logger logger = Logger.getLogger(obj.getClass().getName());
       logger.addHandler(getHandler(methodFile));
       logger.log(Level.OFF, "Method");
   }
   
   private static Handler getHandler(String filename){
       Handler handler=null;
       try {
           handler = new FileHandler(filename , 1024,10);
       } catch (IOException | SecurityException ex) {
           ex.printStackTrace();
       }
       return handler;
   }
   
}
 