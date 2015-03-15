package MockitoTests.UtilTests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
 * Tests to check if the logger and interceptor works
 *
 * @author Samy
 */
@RunWith(MockitoJUnitRunner.class)
public class InterceptorAndLoggerTest {

    private final String param1 = "One";
    private final String param2 = "Two";
    private InvocationContext inContext;

    /**
     * Sets up InvocationContext mock and gets the method used to test the
     * interceptor.
     */
    @Before
    public void setUp() {
        try {
            inContext = Mockito.mock(InvocationContext.class);
            Method method = this.getClass()
                    .getDeclaredMethod("interceptorMethod", new Class[]{String.class, String.class});
            Mockito.when(inContext.getParameters()).thenReturn(new Object[]{param1, param2});
            Mockito.when(inContext.getMethod()).thenReturn(method);
            Mockito.when(inContext.proceed()).
                    thenReturn(method.invoke(this, param1, param2));
        } catch (NoSuchMethodException | SecurityException ex) {
            LoggerUtil.logTest(ex, this);
        } catch (Exception ex) {
            LoggerUtil.logTest(ex, this);
        }
    }

    /**
     * Testing the interceptor to check if parameters will be correct. This will
     * also test logMethod() in the Logger
     */
    @Test
    public void testInterceptor() {
        try {
            String result = (String) LoggingInterceptor.intercept(inContext);
            Assert.assertEquals(param1 + param2, result);
        } catch (Exception ex) {
            LoggerUtil.logTest(ex, this);
            Assert.fail();
        }
    }

    /**
     * Because of the above test of the interceptor a method log file was
     * created. We'll look for that file and check if we can find the correct
     * return value
     */
    public void testLogger() {
        File file = findLastMethodFile();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line=null;
            boolean found = false;
            while((line=br.readLine())!=null){
                if(line.contains("["+param1+param2+"]")){
                    found=true;
                    break;
                }
            }
            if(!found){
                Assert.fail("Couldn't find line in file");
            }
        }
        catch(IOException ex){
            Assert.fail("Problem reading file");
        }
    }

    /**
     * A dummy method to try out the interceptor.
     *
     * @param param1
     * @param param2
     * @return
     */
    private String interceptorMethod(String param1, String param2) {
        return param1 + param2;
    }

    /**
     * Finds the last modified Method file
     * @return
     */
    private File findLastMethodFile() {
        File dir = new File(LoggerUtil.getLoggingDirectory());
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = null;
        long modifiedDate = 0;
        for (File file : files) {
            if (file.getName().contains("method")
                    && modifiedDate < file.lastModified()) {
                lastModifiedFile = file;
            }
        }
        return lastModifiedFile;
    }
}
