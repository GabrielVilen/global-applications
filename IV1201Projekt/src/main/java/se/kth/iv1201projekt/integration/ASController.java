/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import se.kth.iv1201projekt.util.ApplicantDTO;
import se.kth.iv1201projekt.util.LoginErrorException;
import se.kth.iv1201projekt.util.RecruiterDTO;
import se.kth.iv1201projekt.util.RegisterErrorException;

/**
 * Should translate DTO:s into appropriate beans and also handle flow control.
 * This class should handle the ACID principles for the database.
 * 
 * @author Kim
 */
public class ASController {
    
    private ASDatabaseImpl db = new ASDatabaseImpl();
    
    public Object login(String username, String password) throws LoginErrorException {
        Object person = db.login(username, password);
        db.commit();
        return person;
    }
    
    public void register(RecruiterDTO recruiter) throws RegisterErrorException {
        db.register(recruiter);
        db.commit();
    }

    public void register(ApplicantDTO applicant) throws RegisterErrorException {
        db.register(applicant);
        db.commit();
    }
}
