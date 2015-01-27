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
 *
 * @author Kborak
 */
public interface ASDatabase {
    public PersonDTO login(String username, String password) throws LoginErrorException;
    
    public boolean registerApplicant(PersonDTO applicant);
    
    public boolean registerRecruiter(PersonDTO recruiter);
    
    public boolean placeJob(PersonDTO recruiter, Job job);
    
    public boolean applyJob(PersonDTO applicant, Job job);
    
}
