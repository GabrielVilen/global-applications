package MockitoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doAnswer;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.businesslogic.JobBean;
import se.kth.iv1201projekt.businesslogic.RegisterBean;
import se.kth.iv1201projekt.businesslogic.UserBean;

/**
 *
 * @author Kim
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinesslogicLayerTest {
    
    @Mock UserBean userBean;
    @Mock RegisterBean registerBean;
    @Mock JobBean jobBean;
    
    @Test
    public void testLoginCall() {
        String s = "";
        //No parameters are set
        Mockito.when(userBean.login()).thenReturn(s = "fail_1");
        Assert.assertEquals("fail_1", s);
        
        //Setting up for method call
        doAnswer(Answers.CALLS_REAL_METHODS)
                .when(userBean)
                .setUsername("borg");
        
        doAnswer(Answers.CALLS_REAL_METHODS)
                .when(userBean)
                .setPassword("pass");
        
        //Call method
        Mockito.when(userBean.login()).thenReturn(s = "success_1");
        Assert.assertEquals("success_1", s);
    }

}
