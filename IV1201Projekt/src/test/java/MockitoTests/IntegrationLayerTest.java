package MockitoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
    @InjectMocks ASJPADatabaseImpl databaseInjectedMock = new ASJPADatabaseImpl();
    @PersistenceContext(unitName = "se.kth_IV1201Projekt")
    private EntityManager entityManager;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testLogin() throws LoginErrorException {

        Person expected = new Person();
        Mockito.when(databaseMock.login("borg", "pass")).thenReturn(expected);
        
        Person p1 = databaseMock.login("borg", "pass");
        Assert.assertEquals(p1, expected);
        Mockito.verify(databaseMock, Mockito.times(1)).login("borg", "pass");
        
        Person p2 = null;
        Mockito.when(databaseMock.login(any(String.class), any(String.class)))
                .thenThrow(new LoginErrorException("Failed to login."));
        try {
            p2 = databaseMock.login("notAnAccount", "pass");
            Assert.fail("A failed login attempt should throw an exception.");
        } catch (LoginErrorException ex) {
            //Success
        }
    }
    
}
