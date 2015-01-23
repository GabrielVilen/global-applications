/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.util;

/**
 *
 * @author Gabriel
 */
public class Job {
    private String name;
    private Person recruiter;
    private String type;
    private String info;
    private Availability availability;

    public Job(String name, Person recruiter, String type, String info, Availability availability) {
        this.name = name;
        this.recruiter = recruiter;
        this.type = type;
        this.info = info;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Person recruiter) {
        this.recruiter = recruiter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
  
    
}
