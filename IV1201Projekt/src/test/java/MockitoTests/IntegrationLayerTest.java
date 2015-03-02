package MockitoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.ASJPADatabaseImpl;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.integration.model.Role;
import se.kth.iv1201projekt.integration.model.User;
import se.kth.iv1201projekt.util.LoggerUtil;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * This class will test the integration layer. Any database call are simulated
 * and therefore not affecting the actual data in the database.
 * @author Kim
 */
@RunWith(MockitoJUnitRunner.class)
public class IntegrationLayerTest {

    @Mock private ASJPADatabaseImpl databaseMock;
    @Mock private EntityManager entityManager;
    @Mock private Query personQuery;
    private final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    
    /**
     * Defines the simulated data in the database and a few direct calls to the 
     * entity manager.
     */
    @Before
    public void setUp() {
        Person borgPerson;
        User borgUser;
        borgUser = new User("borg", "pass", true, 1);
        borgPerson = new Person(1l, "Per", "Strand", "19671212-1211", "per@strand.kth.se", 1);
        borgPerson.setRoleId(new Role(2l));
        borgPerson.setUsername(borgUser);
        
        Mockito.when(entityManager.find(User.class, "borg")).thenReturn(borgUser);
        Mockito.when(entityManager.createNamedQuery("Person.findByUsername")).thenReturn(personQuery);
        Mockito.when((Person) personQuery.getSingleResult()).thenReturn(borgPerson);
        try {
            Mockito.when(databaseMock.login(any(String.class), any(String.class)))
                    .thenReturn(borgPerson)
                    .thenThrow(new LoginErrorException());
        } catch (LoginErrorException ex) {
            LoggerUtil.logTest(ex, this);
        }
    }
 
    /**
     * Tests the login method.
     * @throws LoginErrorException Thrown if a method call that isn't supposed 
     * to throw the exception, do so.
     */
    @Test
    public void testLogin() throws LoginErrorException {
        String pass = passwordEncryptor.encryptPassword("pass");
        String wrongpass = passwordEncryptor.encryptPassword("wrongpass");
        
        //Test correct login
        Person person = databaseMock.login("borg", pass);
        Mockito.verify(databaseMock, Mockito.times(1)).login("borg", pass);
        Assert.assertNotNull(person);
        
        //Test incorrect login
        Person person2 = null;
        try {
            person2 = databaseMock.login("borg", wrongpass); 
            Assert.fail("A failed login attempt should throw an exception.");
        } catch(LoginErrorException e) {
            //success
            LoggerUtil.logTest(e, this);
        }
        Mockito.verify(databaseMock, Mockito.times(1)).login("borg", wrongpass);
        Assert.assertNull(person2);
        
        //Test with other parameters
        Person person3 = null;
        try {
            person3 = databaseMock.login("borg", null); 
            Assert.fail("A failed login attempt should throw an exception.");
        } catch(LoginErrorException e) {
            //success
            LoggerUtil.logTest(e, this);
        }
        Mockito.verify(databaseMock, Mockito.times(1)).login("borg", null);
        Assert.assertNull(person3);
        
        Person person4 = null;
        try {
            person4 = databaseMock.login(null, pass); 
            Assert.fail("A failed login attempt should throw an exception.");
        } catch(LoginErrorException e) {
            //success
            LoggerUtil.logTest(e, this);
        }
        Mockito.verify(databaseMock, Mockito.times(1)).login(null, pass);
        Assert.assertNull(person4);
        
        Person person5 = null;
        String longPass = passwordEncryptor.encryptPassword("wlnkb2d435ty334%%&\"%%&&&&{{##!!!sfgdhfgfafsfdgfdsf");
        try {
            person5 = databaseMock.login("borg", longPass); 
            Assert.fail("A failed login attempt should throw an exception.");
        } catch(LoginErrorException e) {
            //success
            LoggerUtil.logTest(e, this);
        }
        Mockito.verify(databaseMock, Mockito.times(1)).login("borg", longPass);
        Assert.assertNull(person5);
    }
    
}
