/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Job;
import se.kth.iv1201projekt.util.ErrorMessageFactory;
import se.kth.iv1201projekt.util.LoggerUtil;

/**
 * The job bean which contains method related to the job.
 *
 * @author Gabriel
 */
@Named("jobBean")
@SessionScoped
public class JobBean implements Serializable {

    private String name;
    private String type;
    private String information;
    private Date fromDate;
    private Date toDate;

    @Inject
    private LanguageBean languageBean;

    @EJB
    private ASDBController controller;
    private List<Job> jobList;

    public String getName() {
        return name;
    }

    public void registerJob() {
//        job =  new JobDTO(name, type, information, recruiter, fromDate, toDate); 
//        recruiter = new RecruiterDTO(1, "test", "test", "11", "test", "test", "test");
//        controller.placeJob(recruiter, job);
    }

//    public void setLanguageBean(LanguageBean bean) {
//        this.languageBean = bean;
//    }
    public List<Job> getJobList() {
        try {
            jobList = controller.getAllJobs(languageBean.getLocale());
            return jobList;
        } catch (Exception e) {
            LoggerUtil.logSevere(e, this);
            FacesMessage msg = new FacesMessage(ErrorMessageFactory.getErrorMessage("noserver"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;

    }

    public void setName(String name) {
        this.name = name;
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
