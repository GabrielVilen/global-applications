package MockitoTests.BusinesslogicTests;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.businesslogic.UserBean;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.integration.model.Role;
import se.kth.iv1201projekt.integration.model.User;
import se.kth.iv1201projekt.util.LoggerUtil;
import se.kth.iv1201projekt.exception.LoginErrorException;

/**
 * This class will test the UserBean. Any database call are 
 * simulated and therefore not affecting the actual data in the database.
 * @author Kim
 */
//@Ignore
@RunWith(MockitoJUnitRunner.class)
public class UserBeanTest {
    
    private final UserBean userBean = new UserBean();
    @Mock private ASDBController controller;
    
    /**
     * Defines the simulated data in the database. It also sets the mocked 
     * objects to appropriate test objects.
     */
    @Before
    public void setUp() {
        try {
            User borgUser = new User("borg", "pass", true, 1);
            Person person = new Person(1l, "Per", "Strand", "19671212-1211", "per@strand.kth.se", 1);
            person.setRoleId(new Role(2l));
            person.setUsername(borgUser);
            try {
                Mockito.when(controller.login(any(String.class), any(String.class)))
                        .thenReturn(person)
                        .thenThrow(new LoginErrorException());
            } catch (LoginErrorException ex) {
                LoggerUtil.logTest(ex, this);
            }
            userBean.setASDBController(controller);
        } catch(Exception e) {
            LoggerUtil.logTest(e, this);
            throw e;
        }
    }
    
    /**
     * Tests the login method. This method is also dependant on the logout 
     * method because it is called after every login attempt.
     */
    @Test
    public void testLoginCall() {
        //Both not set
        String s1 = userBean.login();
        Assert.assertEquals("fail_1", s1);
        Assert.assertNull(userBean.getPerson());
        userBean.logout();
        
        //Only password is not set
        userBean.setUsername("borg");
        String s2 = userBean.login();
        Assert.assertEquals("fail_1", s2);
        Assert.assertNull(userBean.getPerson());
        userBean.logout();
        
        //Only username is not set
        userBean.setPassword("pass");
        String s3 = userBean.login();
        Assert.assertEquals("fail_1", s3);
        Assert.assertNull(userBean.getPerson());
        userBean.logout();
        
        //Correct login
        userBean.setUsername("borg");
        userBean.setPassword("pass");
        String s4 = userBean.login();
        Assert.assertTrue(s4.startsWith("success"));
        Assert.assertNotNull(userBean.getPerson());
        userBean.logout();
        
        //Wrong login
        userBean.setUsername("borg");
        userBean.setPassword("wrongpass");
        String s5 = null;
        try{
            s5 = userBean.login();
            //Unhandled return value.
        } catch(NullPointerException e) {
            //Success
        }
        Assert.assertNull(s5);
        Assert.assertNull(userBean.getPerson());
        userBean.logout();
        
        //Empty parameters
        userBean.setUsername("");
        userBean.setPassword("");
        String s6 = null;
        try{
            s6 = userBean.login();
            //Unhandled return value.
        } catch(NullPointerException e) {
            //Success
        }
        Assert.assertNull(s6);
        Assert.assertNull(userBean.getPerson());
        userBean.logout();
    }

}