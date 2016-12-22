/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fujitsu
 */
//@Named(value = "UserNumberBean")
@ManagedBean(name="UserNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable {

    /**
     * Creates a new instance of UserNumberBean
     */
    Integer randomInt;
    Integer userNumber;
    String response;

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
    
    public UserNumberBean() {
        Random randomGR = new Random();
        randomInt = randomGR.nextInt(10);
        System.out.println("Duke's number: " + randomInt);
        
    }
    
    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            return "Yay! You got it!";
        } else {
            return "<p>Sorry, " + userNumber + " isn't it.</p>"
                    + "<p>Guess again...</p>";
        }
       
    }
}
