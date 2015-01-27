package se.kth.iv1201projekt.util;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Samy
 */
public class LoggingInterceptor implements Serializable{

    @AroundInvoke
    public static Object intercept(InvocationContext context) throws Exception{
        Object[] params = context.getParameters();
        String[] formattedParams = new String[context.getParameters().length];
        for(int i=0; i<params.length; i++){
            formattedParams[i] = params[i].toString();
        }
        LoggerUtil.logMethod(context.getTarget().getClass().getName(),context.getMethod().getName() , formattedParams,true);
        Object obj = context.proceed();
        LoggerUtil.logMethod(context.getTarget().getClass().getName(), context.getMethod().getName(),formattedParams,false);
        return obj;
    }
}
    