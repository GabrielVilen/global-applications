package MockitoTests.BusinesslogicTests;

import MockitoTests.UtilTests.ContextMocker;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import junit.framework.Assert;
import org.junit.Before;
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
    
    private UserBean userBean;
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
            FacesContext context = ContextMocker.mockFacesContext();
            HttpSession session = Mockito.mock(HttpSession.class);
            
            try {
                Mockito.when(controller.login(any(String.class), any(String.class)))
                        .thenReturn(person)
                        .thenThrow(new LoginErrorException());
                 Mockito.when(context.getExternalContext().getSession(true)).thenReturn(session);
                 
                
            } catch (LoginErrorException ex) {
                LoggerUtil.logTest(ex, this);
            }
            
            initUserBean();
        } catch(Exception e) {
            LoggerUtil.logTest(e, this);
            throw e;
        }
    }
    
    private void initUserBean() {
        userBean = new UserBean();
        userBean.setASDBController(controller);
        userBean.setTestMode(true);
    }
    
    /**
     * Tests the login method. 
     */
    @Test
    public void testLoginCall() {
        //Both not set
        String s1 = userBean.login();
        Assert.assertEquals("fail_1", s1);
        Assert.assertNull(userBean.getPerson());
        initUserBean();
        
        //Only password is not set
        userBean.setUsername("borg");
        String s2 = userBean.login();
        Assert.assertEquals("fail_1", s2);
        Assert.assertNull(userBean.getPerson());
        initUserBean();
        
        //Only username is not set
        userBean.setPassword("pass");
        String s3 = userBean.login();
        Assert.assertEquals("fail_1", s3);
        Assert.assertNull(userBean.getPerson());
        initUserBean();
        
        //Correct login
        userBean.setUsername("borg");
        userBean.setPassword("pass");
        String s4 = userBean.login();
        Assert.assertTrue(s4.startsWith("success"));
        Assert.assertNotNull(userBean.getPerson());
        initUserBean();
        
        //Wrong login
        userBean.setUsername("borg");
        userBean.setPassword("wrongpass");
        Object s5 = null;
        try{
            s5 = userBean.login();
            //Unhandled return value.
        } catch(NullPointerException e) {
            //Success
        }
        Assert.assertNull(s5);
        Assert.assertNull(userBean.getPerson());
        initUserBean();
        
        //Empty parameters
        userBean.setUsername("");
        userBean.setPassword("");
        Object s6 = null;
        try{
            s6 = userBean.login();
            //Unhandled return value.
        } catch(NullPointerException e) {
            //Success
        }
        Assert.assertNull(s6);
        Assert.assertNull(userBean.getPerson());
        initUserBean();
    }
    
    /**
     * Tests the logout method. 
     */
    @Test
    public void testLogoutCall() {
        userBean.setUsername("borg");
        userBean.setPassword("pass");
        String s4 = userBean.login();
        Assert.assertTrue(s4.startsWith("success"));
        Assert.assertNotNull(userBean.getPerson());
        
        String status = userBean.logout();
        
        Assert.assertNull(userBean.getPerson());
        Assert.assertNull(userBean.getUsername());
        Assert.assertNull(userBean.getPassword());
        Assert.assertTrue(status.startsWith("success"));
        
        initUserBean();
    }
}
