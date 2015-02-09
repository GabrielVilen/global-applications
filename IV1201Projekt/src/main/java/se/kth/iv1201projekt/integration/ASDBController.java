/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.util.logging.Level;
import java.util.logging.Logger;
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


public class ASDBController implements ASDatabase {
    
    private ASJPADatabaseImpl db;
    
    public ASDBController() {
        this.db = new ASJPADatabaseImpl();
    }

    @Override
    public Person login(String username, String password) 
            throws LoginErrorException {
   
        //InitialContext ctx = new InitialContext();
        //ASJPADatabaseImpl db = (ASJPADatabaseImpl)ctx.lookup("ASJPADatabaseImpl");
        return db.login(username, password);
    }
/*
    @Override
    public Applicant loginApplicant(String username, String password) throws LoginErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void register(ApplicantDTO applicant) throws RegisterErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void register(RecruiterDTO recruiter) throws RegisterErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean placeJob(RecruiterDTO recruiter, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean applyJob(ApplicantDTO applicant, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
