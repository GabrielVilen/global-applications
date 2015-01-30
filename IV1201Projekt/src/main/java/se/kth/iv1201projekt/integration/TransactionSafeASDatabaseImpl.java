/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import se.kth.iv1201projekt.util.ApplicantDTO;
import se.kth.iv1201projekt.util.Job;
import se.kth.iv1201projekt.util.LoginErrorException;
import se.kth.iv1201projekt.util.RecruiterDTO;
import se.kth.iv1201projekt.util.RegisterErrorException;

/**
 * This class makes database call transaction-safe, translate DTO:s into 
 * appropriate beans and also handle flow control.
 * 
 * @author Kim
 */
class TransactionSafeASDatabaseImpl implements ASDatabase {
    
    private ASDatabaseImpl db = new ASDatabaseImpl();

    @Override
    public RecruiterDTO loginRecruiter(String username, String password) throws LoginErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApplicantDTO loginApplicant(String username, String password) throws LoginErrorException {
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
    }

}
