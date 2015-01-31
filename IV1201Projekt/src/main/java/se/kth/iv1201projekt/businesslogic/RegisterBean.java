/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * A bean for the registration of new applicants.
 * 
 * @author Samy
 */

@Named("registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
    
    private String firstname;
    private String lastname;
    private String ssn;
    private String email;
    private String role;
    private String birth;
    private int yearsofexperience;
    private String fromtime;
    private String totime;
    private List competences;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }


    public int getYearsofexperience() {
        return yearsofexperience;
    }

    public void setYearsofexperience(int yearsofexperience) {
        this.yearsofexperience = yearsofexperience;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }
    
    public List getCompetences() {
        return competences;
    }

    public void setCompetences(List competences) {
        this.competences = competences;
    }
    
    
    
}
