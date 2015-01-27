/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.kth.iv1201projekt.util.Job;
import se.kth.iv1201projekt.util.LoginErrorException;
import se.kth.iv1201projekt.util.PersonDTO;

/**
 * Represents the database caller for the application station database. 
 * Implements the sql statements and hold the database connection.
 * 
 * @author Kborak
 */
public class ASDatabaseImpl implements ASDatabase {

    private final static String DB_HOST = "jdbc:mysql://localhost:3306/iv1201gasakiproject";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "password";
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private Connection con;
    
    public ASDatabaseImpl() {
        try {
            Class.forName(DB_DRIVER);
            this.con = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ASDatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ASDatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ASDatabaseImpl(String username, String password) {
        try {
            Class.forName(DB_DRIVER);
            this.con = DriverManager.getConnection(DB_HOST, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ASDatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ASDatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public PersonDTO login(String username, String password) throws LoginErrorException {
        try {
            PreparedStatement psUser = con.prepareStatement(SQLQueries.USER_LOGIN);
            PreparedStatement psPerson = con.prepareStatement(SQLQueries.PERSON_INFO);
            psUser.setString(1, username);
            psUser.setString(2, password);
            psUser.executeQuery();
            
            ResultSet rsUser = psUser.getResultSet();
            if(rsUser.next()){
                int personid = rsUser.getInt("personid");
                psPerson.setInt(1, personid);
                
                ResultSet rsPerson = psPerson.executeQuery();
                String name = rsPerson.getString("name");
                String surname = rsPerson.getString("surname");
                String ssn = rsPerson.getString("ssn");
                String email = rsPerson.getString("email");
                int role_id = rsPerson.getInt("role_id");
                String usernamePerson = rsPerson.getString("username");
                
                // not complete, among others the competence list
                PersonDTO person = new PersonDTO(role_id, name, surname, ssn, email, name, username, null, null);
                return person;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ASDatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new LoginErrorException();
    }

    @Override
    public boolean registerApplicant(PersonDTO applicant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerRecruiter(PersonDTO recruiter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean placeJob(PersonDTO recruiter, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean applyJob(PersonDTO applicant, Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
