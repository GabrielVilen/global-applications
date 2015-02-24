/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquillianTests;

import java.io.File;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import se.kth.iv1201projekt.businesslogic.UserBean;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.ASJPADatabaseImpl;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.integration.model.User;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 *
 * @author Kim
 */
//@RunWith(Arquillian.class)
public class IntegrationLayerTest {

    @Ignore
    @Deployment
    public static Archive<?> createDeployment() {
        /*WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClass(ASJPADatabaseImpl.class)
                .addClass(ASDBController.class)
                .addClass(Person.class)
                .addClass(User.class)
                .addClass(LoginErrorException.class)
                //.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                //.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                ;

        war.writeTo(System.err, org.jboss.shrinkwrap.api.formatter.Formatters.VERBOSE);
        System.err.println();
        return war;*/
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ASJPADatabaseImpl.class)
                .addClass(ASDBController.class)
                .addClass(Person.class)
                .addClass(User.class)
                .addClass(LoginErrorException.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                //.addAsResource("glassfish-resources.xml", "setup/glassfish-resources.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public static final String WEBAPP_SRC = "src/main/webapp";
    public static final String USERNAME_TEST = "borg";
    public static final String PASSWORD_TEST = "pass";
    @EJB private ASJPADatabaseImpl db;
    @PersistenceContext(unitName = "se.kth_IV1201Projekt")
    protected EntityManager entityManager;
    @Ignore
    @Test
    public void loginTest() throws LoginErrorException {
        if(db == null) {
            Assert.fail("DB null");
            return;
        }
        //try {
            Person person = db.login(USERNAME_TEST, PASSWORD_TEST);
            if (person == null) {
                Assert.fail("A login attempt failed. Error: Person is null.");
            }
        /*} catch (LoginErrorException ex) {
            Assert.fail("A login attempt failed. Error: " + ex.getMessage());
            ex.printStackTrace();
        }*/
        
        /*
        try {
            Person person = db.login("noneExistingUser", "pass");
            Assert.fail("A failed attempt to login should throw an "
                    + "LoginErrorException. Error: ");
        } catch (LoginErrorException ex) {
            //Success 
        }*/
    }
}
