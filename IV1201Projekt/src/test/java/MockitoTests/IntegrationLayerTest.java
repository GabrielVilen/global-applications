package MockitoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.integration.ASJPADatabaseImpl;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 *
 * @author Kim
 */
@RunWith(MockitoJUnitRunner.class)
public class IntegrationLayerTest {

    @Mock ASJPADatabaseImpl databaseMock;
    //@Mock Person person;
    //@EJB ASJPADatabaseImpl db;
    
    @Test
    public void testCorrectLogin() {
        try {
            Person p = new Person();
            Mockito.when(databaseMock.login("borg", "pass")).thenReturn(p);
            Assert.assertNotNull(p);  
        } catch (LoginErrorException ex) {
            Mockito.doThrow(ex);
        }
         
        /*try {
            db.login("borg", "pass");
            //person = null;
        } catch (LoginErrorException ex) {
            Logger.getLogger(IntegrationLayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    @Ignore
    @Test
    public void testWrongLogin() {
        LoginErrorException leexception = null;
        Person p = null;
        try {
            Mockito.when(databaseMock.login("borg", "wrongpass")).thenReturn(p);
        } catch (LoginErrorException ex) {
            leexception = ex;
        }
        
        Assert.assertNull(leexception); 
        Assert.assertNull(p);
    }
}
