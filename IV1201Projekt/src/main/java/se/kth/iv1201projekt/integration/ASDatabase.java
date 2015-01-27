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
 *
 * @author Kborak
 */
public interface ASDatabase {
    public Object login(String username, String password) throws LoginErrorException;
    
    public void register(ApplicantDTO applicant) throws RegisterErrorException;
    
    public void register(RecruiterDTO recruiter) throws RegisterErrorException;
    
    public boolean placeJob(RecruiterDTO recruiter, Job job);
    
    public boolean applyJob(ApplicantDTO applicant, Job job);
    
}
