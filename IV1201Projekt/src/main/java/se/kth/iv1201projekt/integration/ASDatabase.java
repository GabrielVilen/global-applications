/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * 
 * @author Kborak
 */
public interface ASDatabase {

    public Person login(String username, String password) 
            throws LoginErrorException;
/*
    public Applicant loginApplicant(String username, String password) 
            throws LoginErrorException;

    public void register(ApplicantDTO applicant) throws RegisterErrorException;

    public void register(RecruiterDTO recruiter) throws RegisterErrorException;

    public boolean placeJob(RecruiterDTO recruiter, Job job);

    public boolean applyJob(ApplicantDTO applicant, Job job);*/

}
