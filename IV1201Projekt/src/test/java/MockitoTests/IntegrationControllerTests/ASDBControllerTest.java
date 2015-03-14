/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MockitoTests.IntegrationControllerTests;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import se.kth.iv1201projekt.exception.LoginErrorException;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.ASJPADatabaseImpl;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.integration.model.Role;
import se.kth.iv1201projekt.integration.model.User;
import se.kth.iv1201projekt.util.LoggerUtil;

/**
 *
 * @author Kim
 */
@RunWith(MockitoJUnitRunner.class)
public class ASDBControllerTest {
    
    private ASDBController controllerFascade = new ASDBController();
    @Mock
    private ASJPADatabaseImpl databaseMock = new ASJPADatabaseImpl();
    private final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    
    /**
     * Defines the simulated data in the database and a few direct calls to the
     * database mock.
     * @throws se.kth.iv1201projekt.exception.LoginErrorException If an
     * attempt to log in fails and it wasn't intended to test.
     */
    @Before
    public void setUp() throws LoginErrorException {
        User borgUser = new User("borg", passwordEncryptor.encryptPassword("pass"), true, 1);
        Person borgPerson = new Person(1l, "Per", "Strand", "19671212-1211", "per@strand.kth.se", 1);
        borgPerson.setRoleId(new Role(2l));
        borgPerson.setUsername(borgUser);

        controllerFascade.setASJPADatabaseImpl(databaseMock);
        
        Mockito.when(databaseMock.login(any(String.class), any(String.class)))
                .thenAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws LoginErrorException {
                Object[] args = invocation.getArguments();
                if(args[0] == "borg" && args[1] == "pass") {
                    return borgPerson;
                } else {
                    throw new LoginErrorException();
                }
            }
        });
    }
    
    /**
     * Tests the login method.
     * 
     * @throws LoginErrorException If an unexpected error occurred.
     */
    @Test
    public void loginTest() throws LoginErrorException {
        try {
            String pass = "pass";
            String wrongpass = "wrongpass";

            //Test correct login
            Person person = controllerFascade.login("borg", pass);
            Assert.assertNotNull(person);

            //Test incorrect login
            Person person2 = null;
            try {
                person2 = controllerFascade.login("borg", wrongpass);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person2);

            //Test with other parameters
            Person person3 = null;
            try {
                person3 = controllerFascade.login("borg", null);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person3);

            Person person4 = null;
            try {
                person4 = controllerFascade.login(null, pass);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person4);

            Person person5 = null;
            String longPass = "wlnkb2d435ty334%%&\"%%&&&&{{##!!!sfgdhfgfafsfdgfdsf";
            try {
                person5 = controllerFascade.login("borg", longPass);
                Assert.fail("A failed login attempt should throw an exception.");
            } catch (LoginErrorException e) {
                //success
            }
            Assert.assertNull(person5);

            Person person6 = null;
            String shortPass = "a";
            try {
                person6 = controllerFascade.login("borg", shortPass);
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
