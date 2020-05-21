package com.theSunAndSnow.entity;

public class LoginStatus {
    private boolean status;

    public LoginStatus() {
    }

    public LoginStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
