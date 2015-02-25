/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.iv1201projekt.integration.model.Job;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.LoginErrorException;

/**
 * This class is the connection between the business layer and the integration
 * layer. It will be the public interface of all operations that the integration
 * layer supports.
 *
 * @author Kim
 */
@Stateless(name = "ASDBController")
public class ASDBController implements Serializable {

    @EJB
    private ASJPADatabaseImpl db;

    /**
     * Logins the user by giving a reference to the user's information.
     *
     * @param username The username to match.
     * @param password The password to match.
     * @return The user's information.
     * @throws LoginErrorException Is thrown if it wasn't found or wasn't
     * correct password or if the account was inactive.
     */
    public Person login(String username, String password)
            throws LoginErrorException {
        return db.login(username, password);
    }

    /**
     * Fetches all jobs.
     *
     * @return A list of jobs.
     */
    public List<Job> getAllJobs() {
        return db.getAllJobs();
    }

    /**
     * Fetches all jobs that match any of the specified types.
     *
     * @param typeList The types (of job) to search for.
     * @return A list of jobs.
     */
    public List<Job> getJobs(List<String> typeList) {
        return db.getJobs(typeList);
    }

    public void applyForJob(int id) {
        //db.deleteJob(id);
    }
}
