/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import se.kth.iv1201projekt.integration.model.*;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * This class will use JPA to connect to the database
 *
 * @author Kim
 */
@Stateless(name = "ASJPADatabaseImpl")
public class ASJPADatabaseImpl implements Serializable {

    @PersistenceContext(unitName = "se.kth_IV1201Projekt")
    protected EntityManager entityManager;

    public Person login(String username, String password) throws LoginErrorException {

        try {
            User user = entityManager.find(User.class, username);

            if (user == null) {
                throw new LoginErrorException("user null");
            }

            boolean hasCorrectPassword = user.getPassword().equals(password);

            if (!hasCorrectPassword) {
                throw new LoginErrorException("wrong pass");
            }

            if (!user.getActive()) {
                throw new LoginErrorException("The user is no longer active.");
            }

            Query personQuery = entityManager.createNamedQuery("Person.findByUsername", Person.class);
            personQuery.setParameter("username", user);
            Person person = (Person) personQuery.getSingleResult();

            return person;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Job> getJobList() {
        List jobList;
        try {
            TypedQuery<Job[]> q = (TypedQuery<Job[]>) entityManager.createNamedQuery("Job.findAll");
            jobList = q.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return jobList;
    }

    public void applyForJob(int id) {
        Query query = entityManager.createNamedQuery("Job.deleteJobById", Job.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
