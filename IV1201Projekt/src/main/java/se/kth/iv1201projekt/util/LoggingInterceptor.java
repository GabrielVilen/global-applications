package se.kth.iv1201projekt.util;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * an interceptor called when certain methods are entered and exited
 * @author Samy
 */
public class LoggingInterceptor implements Serializable{
    
    /**
     * Handles the interception of before and after the method annotated
     * @AroundInvoke is called
     * 
     * @param context
     * @return
     */
    @AroundInvoke
    public static Object intercept(InvocationContext context) throws Exception{
        Object[] params = context.getParameters();
        String[] formattedParams = new String[context.getParameters().length];
        for(int i=0; i<params.length; i++){
            formattedParams[i] = params[i].toString();
        }
        LoggerUtil.logMethod(context.getMethod(), formattedParams,true);
        Object obj = context.proceed(); //Let's method to be executed
        LoggerUtil.logMethod(context.getMethod(), formattedParams,false);
        return obj;
    }
}
    