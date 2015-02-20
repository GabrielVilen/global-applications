/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration.model;

import java.util.List;

/**
 *
 * @author Kim
 */
public class Recruiter {
    
    private Person person;
    private Availability availability; 
    private List<CompetenceProfile> competenceProfileList;
    
    public Recruiter(Person person, Availability availability, 
            List<CompetenceProfile> competenceProfileList) {
        this.person = person;
        this.availability = availability;
        this.competenceProfileList = competenceProfileList;
    }
    
}
