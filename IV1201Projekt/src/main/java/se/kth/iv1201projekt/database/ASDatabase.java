/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.database;

import se.kth.IV1201project.user.UserBean;
import se.kth.iv1201projekt.util.Job;
import se.kth.iv1201projekt.util.Person;

/**
 *
 * @author Kborak
 */
public interface ASDatabase {
    public UserBean login(String username, String password, Object role);
    
    public boolean registerApplicant(Person applicant);
    
    public boolean registerRecruiter(Person recruiter);
    
    public boolean placeJob(Person recruiter, Job job);
    
    public boolean applyJob(Person applicant, Job job);
    
}
