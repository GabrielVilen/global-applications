/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.io.Serializable;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import se.kth.iv1201projekt.integration.model.*;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * This class will use JPA to connect to the database 
 * @author Kim
 */
@Stateless
public class ASJPADatabaseImpl implements Serializable {

    //private final EntityManager entityManager;
    /*
    public ASJPADatabaseImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("se.kth_IV1201Projekt");
        this.entityManager = emf.createEntityManager();
    }*/
    
    public Person login(String username, String password) throws LoginErrorException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("se.kth_IV1201Projekt");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        
        try {
            Query userQuery = entityManager.createNamedQuery("User.findByUsername", User.class);
            userQuery.setParameter("username", username);
            User user = (User) userQuery.getSingleResult();

            if(user == null){
                throw new LoginErrorException();
            }
            
            boolean hasCorrectPassword = user.getPassword().equals(password);

            if(!hasCorrectPassword){
                throw new LoginErrorException();
            }
            
            if(!user.getActive()){
                throw new LoginErrorException("The user is no longer active.");
            }

            Query personQuery = entityManager.createNamedQuery("Person.findByUsername", Person.class);
            personQuery.setParameter("username", user.getUsername());
            Person person = (Person) personQuery.getSingleResult();

            return person;
        } catch(Exception e) {
            throw e;
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
            emf.close();
        }
    }

}
