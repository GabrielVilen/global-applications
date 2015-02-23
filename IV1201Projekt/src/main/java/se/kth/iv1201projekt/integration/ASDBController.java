/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * This class should be the connection between the business layer and the 
 * integration layer.
 * 
 * @author Kim
 */

@Stateless(name = "ASDBController")
public class ASDBController implements Serializable {

    @EJB
    private ASJPADatabaseImpl db;
    
    public Person login(String username, String password) 
            throws LoginErrorException {
        return db.login(username, password);
    }

    
}
