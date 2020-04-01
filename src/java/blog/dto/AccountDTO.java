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
public class AccountDTO implements Serializable {

    private String email, password, name, role;
    private int status;

    public AccountDTO(String email, String name, String role) {
        this.email = email;
        this.name = name;
        this.role = role;

    }

    public AccountDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public AccountDTO(String email, String name, String role, int status) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public AccountDTO(String email, String password, String name, String role, int status) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

}
