package MockitoTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 *
 * @author Kim
 */
@RunWith(MockitoJUnitRunner.class)
public class IntegrationLayerTest {

    @Mock private ASJPADatabaseImpl databaseMock;
    @Mock private EntityManager entityManager;
    @Mock private Query personQuery;
    private final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    
    
    @Before
    public void setUp() throws LoginErrorException {
        Person borgPerson;
        User borgUser;
        borgUser = new User("borg", "pass", true, 1);
        borgPerson = new Person(1l, "Per", "Strand", "19671212-1211", "per@strand.kth.se", 1);
        borgPerson.setRoleId(new Role(2l));
        borgPerson.setUsername(borgUser);
        
        Mockito.when(entityManager.find(User.class, "borg")).thenReturn(borgUser);
        Mockito.when(entityManager.createNamedQuery("Person.findByUsername")).thenReturn(personQuery);
        Mockito.when((Person) personQuery.getSingleResult()).thenReturn(borgPerson);
        Mockito.when(databaseMock.login(any(String.class), any(String.class)))
                .thenReturn(borgPerson)
                .thenThrow(new LoginErrorException());
    }
    
    @Test
    public void testLogin() throws LoginErrorException {

        String pass = passwordEncryptor.encryptPassword("pass");
        String wrongpass = passwordEncryptor.encryptPassword("wrongpass");
        
        Person person = databaseMock.login("borg", pass);
        Mockito.verify(databaseMock, Mockito.times(1)).login("borg", pass);
        Assert.assertNotNull(person);
        
        Person person2 = null;
        try {
            person2 = databaseMock.login("borg", wrongpass); 
            Assert.fail("A failed login attempt should throw an exception.");
        } catch(LoginErrorException e) {
            //success
        }
        Mockito.verify(databaseMock, Mockito.times(1)).login("borg", wrongpass);
        Assert.assertNull(person2);
    }
    
}
