/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.jasypt.util.password.StrongPasswordEncryptor;
import se.kth.iv1201projekt.integration.model.*;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * This class will handle the connection to the database and the sending of
 * queries.
 * @author Kim
 * TODO: Fixa exceptions i BEANS
 */
@Stateless(name = "ASJPADatabaseImpl")
public class ASJPADatabaseImpl implements Serializable {

    @PersistenceContext(unitName = "se.kth_IV1201Projekt")
    protected EntityManager entityManager;
    
    /**
     * Logins the user by giving a reference to the user's information.
     * @param username The username to match.
     * @param password The password to match.
     * @return The user's information.
     * @throws LoginErrorException Is thrown if it wasn't found or 
     * wasn't correct password or if the account was inactive.
     */
    public Person login(String username, String password) throws LoginErrorException {

        try {
            User user = entityManager.find(User.class, username);
   
            if(user == null){
                throw new LoginErrorException();
            }
            
            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            boolean hasCorrectPassword = passwordEncryptor.checkPassword(user.getPassword(), password);

            if(!hasCorrectPassword){
                throw new LoginErrorException();
            }

            if(!user.getActive()){
                throw new LoginErrorException();
            }

            Query personQuery = entityManager.createNamedQuery("Person.findByUsername", Person.class);
            personQuery.setParameter("username", user);
            Person person = (Person) personQuery.getSingleResult();

            return person;
        } catch(Exception e) {
            LoginErrorException lee = new LoginErrorException();
            lee.addSuppressed(e);
            throw lee;
        } 
    }
    
    /**
     * Fetches all jobs.
     * @return A list of jobs.
     */
     public List<Job> getAllJobs() {
        List jobList;
        try {
            TypedQuery<Job[]> q = (TypedQuery<Job[]>) entityManager.createNamedQuery("Job.findAll");
            jobList = q.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return jobList;
    }
    
    /**
     * Fetches all jobs that match any of the specified types.
     * @param typeList The types (of job) to search for.
     * @return A list of jobs.
     */
    public List<Job> getJobs(List<String> typeList) {
        List<Job> jobList = new ArrayList<>();
        for(String type : typeList) {
            Query jobByTypeQuery = entityManager.createNamedQuery("Job.findByType", Job.class);
            jobByTypeQuery.setParameter("type", type);
            List<Job> jlist = jobByTypeQuery.getResultList();
            for (Job j : jlist) {
                if(jobList.contains(j)) continue;
                jobList.add(j);
            }
        }
        return jobList;
    }

}
