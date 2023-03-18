package com.cytix.model;
import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String userName;
    private String userEmail;
    private String accountType;
    private String password;

    public User(String userName, String userEmail, String password) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String printable() {
        return "\nUsername: " + getUserName()
                +"\nID: " + getId()
                +"\nEmail: " + getUserEmail();
    }
}
