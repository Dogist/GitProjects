/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.immediate;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author maue
 */
public class Customer {

    private String lastName;
    private String firstName;
    private boolean useAccount;
    private String accountIBAN;
    private String accountBIC;

    /**
     * Creates a new instance of Costumer
     */
    public Customer() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isUseAccount() {
        return useAccount;
    }

    public void setUseAccount(boolean useAccount) {
        this.useAccount = useAccount;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public void setAccountIBAN(String accountIBAN) {
        this.accountIBAN = accountIBAN;
    }

    public String getAccountBIC() {
        return accountBIC;
    }

    public void setAccountBIC(String accountBIC) {
        this.accountBIC = accountBIC;
    }

    public Object save() {
        return "success";
    }
}
