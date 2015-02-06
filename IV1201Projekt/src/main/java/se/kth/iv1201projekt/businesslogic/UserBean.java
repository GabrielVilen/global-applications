/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.iv1201projekt.integration.ASDatabase;
import se.kth.iv1201projekt.integration.ASDBController;

/**
 *
 * @author Gabriel
 */
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());
    private static final long serialVersionUID = 1L;
    @Inject private ASDBController controller;

    private String username;
    private String password;

    // check role_id or split up
    public boolean login(String username, String password) {
        try {
            controller.loginApplicant(username, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
    
    

}
