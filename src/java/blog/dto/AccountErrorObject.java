/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dto;

import java.io.Serializable;

/**
 *
 * @author Tan
 */
public class AccountErrorObject implements Serializable{

    private String emailError, passwordError, nameError;

    public AccountErrorObject(String emailError, String passwordError, String nameError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.nameError = nameError;
    }

    public AccountErrorObject() {
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

}
