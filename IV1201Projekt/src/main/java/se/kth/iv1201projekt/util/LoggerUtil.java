package se.kth.iv1201projekt.util;

import static com.mysql.jdbc.Util.stackTraceToString;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A class that handles all logging done by the application.
 * @author Samy
 */
public class LoggerUtil implements Serializable {
    
    private static final String logDir="../GasakiLogs";
    private static final String exceptionFile = "/exceptionLog%g.log";
    private static final String methodFile ="/methodLog%g.log";
    
    /**
     * Logs severe exceptions.
     * @param e the exception itself
     * @param exceptionClass the class the exception was called from
     */
   public static void logSevere(Exception e, Object exceptionClass){
       Logger logger = Logger.getLogger(exceptionClass.getClass().getName());
       logger.addHandler(getHandler(exceptionFile));
       logger.log(Level.SEVERE,stackTraceToString(e),e);
   }
   
     /**
     * Logs minor exceptions.
     * @param e the exception itself
     * @param exceptionClass the class the exception was called from
     */
   public static void logMinor(Exception e, Object exceptionClass){
       Logger logger = Logger.getLogger(exceptionClass.getClass().getName());
       logger.addHandler(getHandler(exceptionFile));
       logger.log(Level.FINE, stackTraceToString(e), e);
   }
   
   /**
    * Logs parameters when a method annotated @Aroundinvoke is called and exited
    * @param method Class of where the method is called
    * @param formattedParams the parameters invoked
    * @param before if it's before it's called or after it has exited.
    */
   public static void logMethod(Method method, String[] formattedParams ,boolean before){
       Logger logger = Logger.getLogger(method.getClass().getName());
       logger.addHandler(getHandler(methodFile));
       if(before)
           logger.log(Level.OFF, "Before " + method.getName() + formattedParams.toString());
       else
           logger.log(Level.OFF, "After " + method.getName() + formattedParams.toString());
   }
   
   private static Handler getHandler(String filename){
       Handler handler=null;
       try {
           new File(logDir).mkdir();
           handler = new FileHandler(logDir + filename , 1024,10);
           handler.setFormatter(new SimpleFormatter());
       } catch (IOException | SecurityException ex) {
           ex.printStackTrace();
       }
       return handler;
   }
   
}
 