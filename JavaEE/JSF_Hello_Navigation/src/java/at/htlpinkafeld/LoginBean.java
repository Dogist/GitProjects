/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld;

/**
 *
 * @author Martin Six
 */
public class LoginBean {

    private static final String USER="Franz";
    private static final String PASS="k";
    
    private String username;
    private String password;
    /**
     * Creates a new instance of Login
     */
    public LoginBean() {
        password="hellp";
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
        this.password = password;
    }
    
    public String isUserOK(){
        if(username!=null && username.contentEquals(USER)){
            if(password!=null && password.contentEquals(PASS))
            return "loginSuccess";
        }
        return "loginFailure";
    }
}
