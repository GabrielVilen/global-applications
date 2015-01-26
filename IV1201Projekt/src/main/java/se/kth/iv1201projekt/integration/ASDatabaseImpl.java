/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import se.kth.iv1201projekt.businesslogic.UserBean;
import se.kth.iv1201projekt.util.Job;
import se.kth.iv1201projekt.util.Person;

/**
 *
 * @author Kborak
 */
public class ASDatabaseImpl implements ASDatabase {

    @Override
    public UserBean login(String username, String password, Object role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerApplicant(Person applicant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerRecruiter(Person recruiter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean placeJob(Person recruiter, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean applyJob(Person applicant, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
