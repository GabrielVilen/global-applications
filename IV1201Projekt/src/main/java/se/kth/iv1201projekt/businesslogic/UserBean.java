package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.ErrorMessageFactory;
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
    private boolean isTest = false;

    /**
     * This method is only used during testing to set a mocked controller.
     * @param controller is the mocked controller for testing.
     */
    public void setASDBController(ASDBController controller) {
        this.controller = controller;
    }
    
    /**
     * Sets the state as test mode. This includes ignoring http session errors.
     * @param isTest the state to set. True is for testing and false if not.
     */
    public void setTestMode(boolean isTest) {
        this.isTest = isTest;
    }
    
    /**
     * Logs in the user and fetches the user's information.
     * Username and password need to be set before calling this method.
     * This method will also clear the password after the database call.
     * @return A status text which will be handled by the JSF.
     */
    public String login() {
        if (username == null || password == null) {
            return "fail_1";
        }

        try {
            person = controller.login(username, password);
            password = null;
            
            if (!isTest) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                        getExternalContext().getSession(true);
                session.setAttribute("user", username);
                session.setAttribute("role", person.getRoleId().getName());
            }     
            return "success";
        } catch (Exception ex) {
            logExceptionAndShowError(ex,"wronglogin");
            return null;
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

        if (!isTest) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                        getExternalContext().getSession(true);
            session.removeAttribute("user");
            session.removeAttribute("role");
        }
        return "success";
    }

    /**
     * Checks the beans status to determine if the user is logged in.
     * @return True if logged in else False.
     */
    public boolean isLoggedIn() {
        return person != null;
    }
    
    /**
     * Checks if the logged in user is a recruiter or a applicant.
     * @return true if it's a recruiter and false if it's an applicant.
     */
    public boolean isRecruiter(){
        if(person == null) {
            return false;
        }
        return person.getRoleId().getName().equals("recruit");
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
     * Store the password for further use.
     * @param password The password to use for operations.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }
    
    /**
     * Logs the exception and shows the user an errormessage
     * @param e exception
     * @param errorKey key to the errormessage in error.property
     */
    private void logExceptionAndShowError(Exception e,String errorKey) {
        LoggerUtil.logSevere(e, this);
        FacesMessage msg = new FacesMessage(ErrorMessageFactory.getErrorMessage(errorKey));
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
