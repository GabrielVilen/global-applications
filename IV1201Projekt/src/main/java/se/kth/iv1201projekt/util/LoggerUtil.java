package se.kth.iv1201projekt.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for logging exceptions.
 * @author Samy
 */
public class LoggerUtil implements Serializable {

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
   
   public static void logMethod(Object obj, String methodname, String[] formattedParams ,boolean before){
       Logger logger = Logger.getLogger(obj.getClass().getName());
       logger.addHandler(getHandler(methodFile));
       if(before)
           logger.log(Level.OFF, "Before " + methodname + formattedParams.toString());
       else
           logger.log(Level.OFF, "After " + methodname + formattedParams.toString());
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
 