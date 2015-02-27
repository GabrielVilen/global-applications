package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.jasypt.util.password.StrongPasswordEncryptor;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.LoggerUtil;

/**
 * This bean is used for logging in service and to store the user's personal
 * information.
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

    /**
     * Logs in the user and fetches the user's information.
     * Username and password need to be set before calling this method.
     * @return A status text which will be handled by the JSF.
     */
    public String login() {
        System.out.println("controller=" + controller + "username=" + username + "password=" + password);
        if (username == null || password == null) {
            return "fail_1";
        }

        try {
            person = controller.login(username, password);
            String role = person.getRoleId().getName();
            return "success_" + role;
        } catch (Exception e) {
            LoggerUtil.logSevere(e, this);
            return "fail_2";
        }
    }
    
    /**
     * Applies for the specified job with the current logged in user.
     * TODO: Reference the person who applies.
     * @param id The job's id.
     */
    public void applyForJob(int id) {
        controller.applyForJob(id);
    }
  
    /**
     * Logouts the user by resetting the beans state.
     * @return The page to redirect to.
     */
    public String logout() {
        person = null;
        username = null;
        password = null;
        return "index.xhtml";
    }

    /**
     * Checks the beans status to determine if the user is logged in.
     * @return True if logged in else False.
     */
    public boolean isLoggedIn() {
        return person != null;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Store the username for further use
     * @param username The username to use for operations.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Store the password as an encrypted password for further use.
     * @param password The password to use for operations. This method will 
     * encrypt the password.
     */
    public void setPassword(String password) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);
        this.password = encryptedPassword;
    }

    public Person getPerson() {
        return person;
    }

}
