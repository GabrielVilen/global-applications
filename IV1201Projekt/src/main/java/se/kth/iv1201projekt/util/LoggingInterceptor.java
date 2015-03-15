package se.kth.iv1201projekt.util;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * an interceptor called when certain methods are entered and exited
 *
 * @author Samy
 */
public class LoggingInterceptor implements Serializable {

    /**
     * Handles the interception of before and after the method annotated
     * Logs parameters given to the method and the return value.
     * 
     * @throws java.lang.Exception
     * @AroundInvoke is called
     * @param context
     * @return
     */
    @AroundInvoke
    public static Object intercept(InvocationContext context) throws Exception {
        Object[] params = context.getParameters();
        String[] formattedParams = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            formattedParams[i] = params[i].toString();
        }
        LoggerUtil.logMethod(context.getMethod(), formattedParams, true);
        Class objClass = context.getMethod().getReturnType();
        Object obj = objClass.newInstance(); //This is to make sure that the object has the same class as the return value
        obj = (Object) context.proceed(); //Let's method be exectued and returns return value
        LoggerUtil.logMethod(context.getMethod(), new String[]{obj.toString()}, false);
        return obj;
    }
}
