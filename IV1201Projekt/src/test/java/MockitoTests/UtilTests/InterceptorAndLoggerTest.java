package MockitoTests.UtilTests;

import java.lang.reflect.Method;
import javax.interceptor.InvocationContext;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.util.LoggerUtil;
import se.kth.iv1201projekt.util.LoggingInterceptor;

/**
 *  Tests to check if the logger and interceptor works
 * @author Samy
 */
@RunWith(MockitoJUnitRunner.class)
public class InterceptorAndLoggerTest {
    
    private final String param1 = "One";
    private final String param2 = "Two";
    private InvocationContext inContext;
    
    /**
     * Sets up InvocationContext mock 
     * and gets the method used to test the interceptor.
     */
    @Before
    public void setUp() {
        try {
            inContext = Mockito.mock(InvocationContext.class);
            Method method = this.getClass()
                    .getDeclaredMethod("interceptorMethod",new Class[]{String.class,String.class});
            Mockito.when(inContext.getParameters()).thenReturn(new Object[]{param1,param2});
            Mockito.when(inContext.getMethod()).thenReturn(method);
            Mockito.when(inContext.proceed()).
                    thenReturn(method.invoke(this,param1,param2));
        } catch (NoSuchMethodException | SecurityException ex) {
            LoggerUtil.logTest(ex, this);
        } catch (Exception ex) {
            LoggerUtil.logTest(ex, this);
        } 
    }
    
    /**
     * Testing the interceptor to check if parameters will be correct.
     * This will also test logMethod() in the Logger
     */
    @Test
    public void testInterceptor(){
        try {
            String result = (String) LoggingInterceptor.intercept(inContext);
            Assert.assertEquals(param1+param2,result);
        } catch (Exception ex) {
           LoggerUtil.logTest(ex, this);
           Assert.fail();
        }
    }
    
    /**
     * A dummy method to try out the interceptor.
     * @param param1
     * @param param2
     * @return 
     */
    private String interceptorMethod(String param1, String param2){
        return param1+param2;
    }
}
