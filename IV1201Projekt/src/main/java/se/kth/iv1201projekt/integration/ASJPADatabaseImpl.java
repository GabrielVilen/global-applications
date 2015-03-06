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
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.jasypt.util.password.StrongPasswordEncryptor;
import se.kth.iv1201projekt.integration.model.*;
import se.kth.iv1201projekt.util.LoggingInterceptor;
import se.kth.iv1201projekt.exception.LoginErrorException;

/**
 * This class will handle the connection to the database and the sending of
 * queries.
 *
 * @author Kim
 */
@Stateless(name = "ASJPADatabaseImpl")
public class ASJPADatabaseImpl implements Serializable {

    @PersistenceContext(unitName = "se.kth_IV1201Projekt")
    protected EntityManager entityManager;
    private Query personQuery = null;

    /**
     * This method should only be used for testing.
     *
     * @param entityManager The mocked entity manager instance.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * This method should only be used for testing.
     *
     * @param personQuery The mocked query instance for fetching a person.
     */
    public void setPersonQuery(Query personQuery) {
        this.personQuery = personQuery;
    }

    /**
     * Logins the user by giving a reference to the user's information.
     *
     * @param username The username to match.
     * @param password The password to match. Must be plain text.
     * @return The user's information.
     * @throws LoginErrorException Is thrown if it wasn't found or wasn't
     * correct password or if the account was inactive.
     */
    @Interceptors(LoggingInterceptor.class)
    public Person login(String username, String password) throws LoginErrorException {

        System.out.println("username = " + username + " password: " + password);
        User user = entityManager.find(User.class, username);

        if (user == null) {
            throw new LoginErrorException("The user could not be fetched.");
        }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        boolean hasCorrectPassword = passwordEncryptor.checkPassword(password, user.getPassword());

        if (!hasCorrectPassword) {
            throw new LoginErrorException("Incorrect user-password combination.");
        }

        if (!user.getActive()) {
            throw new LoginErrorException("Inactivated user.");
        }

        Person person;
        if (personQuery == null) {
            Query personQuery = entityManager.createNamedQuery("Person.findByUsername", Person.class);
            personQuery.setParameter("username", user);
            person = (Person) personQuery.getSingleResult();
        } else {
            person = (Person) personQuery.getSingleResult();
        }

        return person;
    }

    /**
     *
     * Fetches all jobs.
     *
     * @param language The language tag to use. For example "sv" for swedish.
     * @return A list of jobs.
     */
    public List<Job> getAllJobs(String language) {
        List jobList = new ArrayList();
        String query;
        try {
            char c = language.charAt(0);
            if(c >= 97 || c <= 122) {
                String upperCase = c + "";
                upperCase = upperCase.toUpperCase();
                language = upperCase + language.substring(1);
            }
            
            if (language.equalsIgnoreCase("en")) {
                query = "Job.findAll";
            } else {
                query = "Job" + language + ".findAll";
            }
            TypedQuery<Job[]> q = (TypedQuery<Job[]>) entityManager.createNamedQuery(query);
            jobList = q.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return jobList;
    }

    /**
     * Fetches all jobs that match any of the specified types.
     *
     * @param typeList The types (of job) to search for.
     * @return A list of jobs.
     */
    public List<Job> getJobs(List<String> typeList) {
        List<Job> jobList = new ArrayList<>();
        for (String type : typeList) {
            Query jobByTypeQuery = entityManager.createNamedQuery("Job.findByType", Job.class);
            jobByTypeQuery.setParameter("type", type);
            List<Job> jlist = jobByTypeQuery.getResultList();
            for (Job j : jlist) {
                if (jobList.contains(j)) {
                    continue;
                }
                jobList.add(j);
            }
        }
        return jobList;
    }

}
