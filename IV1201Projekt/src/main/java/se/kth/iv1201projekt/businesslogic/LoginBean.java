/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import se.kth.iv1201projekt.integration.ASController;

/**
 *
 * @author Gabriel
 */
@Named("loginBean")
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ASController controller = new ASController();

    private String username;
    private String password;

    public boolean login(String username, String password) {
        try {
            controller.login(username, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//
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
        System.out.println("username set to " + username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("password set to " + password);
    }
    
    

}
