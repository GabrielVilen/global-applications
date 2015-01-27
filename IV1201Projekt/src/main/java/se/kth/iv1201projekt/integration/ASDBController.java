/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import se.kth.iv1201projekt.util.Job;
import se.kth.iv1201projekt.util.LoginErrorException;
import se.kth.iv1201projekt.util.PersonDTO;

/**
 * Ska översätta dtoer till bönor och hantera flödeskontroll.
 * 
 * @author Kim
 */
public class ASDBController implements ASDatabase {

    @Override
    public PersonDTO login(String username, String password) throws LoginErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerApplicant(PersonDTO applicant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerRecruiter(PersonDTO recruiter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean placeJob(PersonDTO recruiter, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean applyJob(PersonDTO applicant, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
