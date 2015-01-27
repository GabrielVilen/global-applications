/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import se.kth.iv1201projekt.businesslogic.LoginBean;
import se.kth.iv1201projekt.util.Job;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * Should translate DTO:s into appropriate beans and also handle flow control.
 * This class should handle the ACID principles for the database.
 * 
 * @author Kim
 */
public class ASController {
    
    public LoginBean login(String username, String password) throws LoginErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
