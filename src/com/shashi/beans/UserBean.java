package com.shashi.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserBean implements Serializable {

    public UserBean() {
    }

    public UserBean(String username, Long mobile, String email, String address, int pincode, String password) {
        super();
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.pincode = pincode;
        this.password = password;
    }

    private String username;
    private Long mobile;
    private String email;
    private String address;
    private int pincode;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return validateUsername() && validateMobile() && validateEmail() && validateAddress() && validatePincode() && validatePassword();
    }

    private boolean validateUsername() {
        return username != null && !username.trim().isEmpty() && !username.matches(".*\\d.*");
    }

    private boolean validateMobile() {
        return mobile != null && String.valueOf(mobile).matches("\\d{10}");
    }

    private boolean validateEmail() {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean validateAddress() {
        return address != null && !address.trim().isEmpty();
    }

    private boolean validatePincode() {
        return pincode >= 100000 && pincode <= 999999;
    }

    private boolean validatePassword() {
        return password != null && password.length() >= 6;
    }
}
