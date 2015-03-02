package MockitoTests;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * 
 * @author Kim
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinesslogicLayerTest {
    
    @InjectMocks UserBean userBean;
    @Mock ASDBController controller;
    
    @Before
    public void setUp() {
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
    }
    
    @Test
    public void testLoginCall() {
        //Both null
        String s1 = userBean.login();
        Assert.assertEquals("fail_1", s1);
        Assert.assertNull(userBean.getPerson());
        
        //Password is null
        userBean.setUsername("borg");
        String s2 = userBean.login();
        Assert.assertEquals("fail_1", s2);
        Assert.assertNull(userBean.getPerson());
        
        //Nothing null
        userBean.setPassword("pass");
        String s3 = userBean.login();
        Assert.assertTrue(s3.startsWith("success_"));
        Assert.assertNotNull(userBean.getPerson());
        
        //Wrong login
        userBean.setPassword("wrongpass");
        String s4 = userBean.login();
        Assert.assertEquals("fail_2", s4);
        Assert.assertNull(userBean.getPerson());
    }

}
