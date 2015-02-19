/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.mockito;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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

    @Mock
    ASJPADatabaseImpl databaseMock;
    @Mock 
    Person person;
    
    @Test
    public void testCorrectLogin() {
        
        try {
            Mockito.when(databaseMock.login("borg", "pass")).thenReturn(person);
            Assert.assertNotNull(person);  
        } catch (LoginErrorException ex) {
            Mockito.doThrow(ex);
        }
        
    }
    
    @Test(expected = LoginException.class)
    public void testWrongLogin() throws LoginErrorException {
        Mockito.when(databaseMock.login("borg", "wrongpass")).thenThrow(new LoginException("Expected wrong pass."));
        Mockito.when(databaseMock.login("borg1324254", "pass")).thenThrow(new LoginException("Expected wrong username."));
    }
}
