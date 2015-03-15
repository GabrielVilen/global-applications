package MockitoTests.UtilTests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Here we try to log a method and later read the last modified file 
     * too see if what we tried to log is there.
     */
    @Test
    public void testLogger() {
        String[] params=null;
        try {
            Method method = this.getClass()
                    .getDeclaredMethod("interceptorMethod", new Class[]{String.class, String.class});
            params = new String[]{"Hej", "Hå"};
            LoggerUtil.logMethod(method, params, true);
        } catch (NoSuchMethodException | SecurityException ex) {
            Assert.fail();
        }
        
        File file = findLastMethodFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;
            String result = Arrays.toString(params);
            while ((line = br.readLine()) != null) {
                if (line.contains(result)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Assert.fail("Couldn't find line in file");
            }
        } catch (IOException ex) {
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
     *
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
                modifiedDate=file.lastModified();
            }
        }
        return lastModifiedFile;
    }
}
