/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Person;

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
        if(username == null || password == null) return "fail_1";
        
        try {
            person = controller.login(username, password);
            String role = person.getRoleId().getName();
            return "success_" + role;
        } catch (Exception e) {
            return "fail_2";
        }
    }
    

//    public boolean register() {
//        try {
//            controller.register();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


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
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }
    
    

}
