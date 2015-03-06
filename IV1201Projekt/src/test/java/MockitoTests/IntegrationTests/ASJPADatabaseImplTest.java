package MockitoTests.IntegrationTests;

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
import org.junit.Ignore;
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
import se.kth.iv1201projekt.exception.LoginErrorException;

/**
 * This class will test the integration layer. Any database call are simulated
 * and therefore not affecting the actual data in the database.
 *
 * @author Kim
 */
@RunWith(MockitoJUnitRunner.class)
public class ASJPADatabaseImplTest {

    @Mock
    private EntityManager entityManagerMock;
    @Mock
    private Query personQueryMock;
    private final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    private ASJPADatabaseImpl databaseFacade = new ASJPADatabaseImpl(); // Class to be tested.

    /**
     * Defines the simulated data in the database and a few direct calls to the
     * entity manager.
     */
    @Before
    public void setUp() {
        try {
            User borgUser = new User("borg", passwordEncryptor.encryptPassword("pass"), true, 1);
            Person borgPerson = new Person(1l, "Per", "Strand", "19671212-1211", "per@strand.kth.se", 1);
            borgPerson.setRoleId(new Role(2l));
            borgPerson.setUsername(borgUser);

            Mockito.when(entityManagerMock.find(User.class, "borg")).thenReturn(borgUser);
            Mockito.when(entityManagerMock.createNamedQuery("Person.findByUsername")).thenReturn(personQueryMock);
            Mockito.when((Person) personQueryMock.getSingleResult()).thenReturn(borgPerson);

            databaseFacade.setEntityManager(entityManagerMock);
            databaseFacade.setPersonQuery(personQueryMock);
        } catch (Exception e) {
            LoggerUtil.logTest(e, this);
            throw e;
        }
    }

    /**
     * Tests the login method. 
     *
     * @throws LoginErrorException Thrown if a method call that isn't supposed
     * to throw the exception, do so.
     */
    @Test
    public void testLogin() throws LoginErrorException {
        try {
            String pass = "pass";
            String wrongpass = "wrongpass";

            //Test correct login
            Person person = databaseFacade.login("borg", pass);
            Assert.assertNotNull(person);

            //Test incorrect login
            Person person2 = null;
            try {
                person2 = databaseFacade.login("borg", wrongpass);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person2);

            //Test with other parameters
            Person person3 = null;
            try {
                person3 = databaseFacade.login("borg", null);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person3);

            Person person4 = null;
            try {
                person4 = databaseFacade.login(null, pass);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person4);

            Person person5 = null;
            String longPass = "wlnkb2d435ty334%%&\"%%&&&&{{##!!!sfgdhfgfafsfdgfdsf";
            try {
                person5 = databaseFacade.login("borg", longPass);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person5);

            Person person6 = null;
            String shortPass = "a";
            try {
                person6 = databaseFacade.login("borg", shortPass);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person5);
        } catch (Exception e) {
            LoggerUtil.logTest(e, this);
            throw e;
        }
    }

    
}
