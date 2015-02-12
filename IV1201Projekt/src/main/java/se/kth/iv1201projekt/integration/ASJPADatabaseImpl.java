/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.io.Serializable;
import javax.ejb.Stateless;
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

@Stateless(name = "ASJPADatabaseImpl")
public class ASJPADatabaseImpl implements Serializable {

    public Person login(String username, String password) throws LoginErrorException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("se.kth_IV1201Projekt");
        EntityManager entityManager = emf.createEntityManager();
        //entityManager.getTransaction().begin();
        
        try {
            User user = entityManager.find(User.class, username);
   
            if(user == null){
                throw new LoginErrorException("user null");
            }
            
            boolean hasCorrectPassword = user.getPassword().equals(password);

            if(!hasCorrectPassword){
                throw new LoginErrorException("wrong pass");
            }

            if(!user.getActive()){
                throw new LoginErrorException("The user is no longer active.");
            }

            //Query personQuery = entityManager.createNamedQuery("Person.findByUsername", Person.class);
            Query personQuery = entityManager.createQuery("SELECT p FROM Person p WHERE p.username = :username");
            personQuery.setParameter("username", user);
            Person person = (Person) personQuery.getSingleResult();
            
            return person;
        } catch(Exception e) {
            throw e;
        } finally {
            //entityManager.getTransaction().commit();
            entityManager.close();
            emf.close();
        }
    }

}
