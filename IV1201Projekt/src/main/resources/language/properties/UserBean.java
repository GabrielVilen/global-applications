/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import org.jasypt.util.password.StrongPasswordEncryptor;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.ErrorMessageFactory;
import se.kth.iv1201projekt.util.LoggerUtil;

/**
 *
 * @author Gabriel
 */
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private ASDBController controller;

    private String username;
    private String password;
    private Person person;

    public String login() {
        System.out.println("controller=" + controller + "username=" + username + "password=" + password);
        try {
            person = controller.login(username, password);
            String role = person.getRoleId().getName();
            return "success_" + role;
        } catch (Exception e) {
            LoggerUtil.logSevere(e, this);
            FacesMessage msg = new FacesMessage(ErrorMessageFactory.getErrorMessage("wronglogin"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
    
    public void applyForJob(int id) {
        controller.applyForJob(id);
    }
  
    public String logout() {
        person = null;
        return "index.xhtml";
    }

    public boolean isLoggedIn() {
        return person != null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);
        this.password = encryptedPassword;
    }

    public Person getPerson() {
        return person;
    }

}
