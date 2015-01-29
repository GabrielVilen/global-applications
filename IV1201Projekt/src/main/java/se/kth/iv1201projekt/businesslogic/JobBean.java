/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import se.kth.iv1201projekt.util.RecruiterDTO;

/**
 *
 * @author Gabriel
 */
@Named("jobBean")
@SessionScoped
public class JobBean implements Serializable {
    
    private String name;
    private String type;
    private String information;
    private RecruiterDTO recruiter;
    private Date fromDate;
    private Date toDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void print() {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("hello from JOB BEAN");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public RecruiterDTO getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(RecruiterDTO recruiter) {
        this.recruiter = recruiter;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }


    
    
}