package com.theSunAndSnow.entity;

public class Customer {
    private Integer id;
    private String telephone;
    private String password;
    private String name;
    private String sex;
    private boolean loginStatus;

    public Customer() {
    }

    public Customer(String telephone, String password) {
        this.telephone = telephone;
        this.password = password;
    }

    public Customer(Integer id, String telephone, String password) {
        this.id = id;
        this.telephone = telephone;
        this.password = password;
    }

    public Customer(Integer id, String telephone, String password, String name, boolean loginStatus) {
        this.id = id;
        this.telephone = telephone;
        this.password = password;
        this.name = name;
        this.loginStatus = loginStatus;
    }

    public Customer(Integer id, String telephone, String password, String name, String sex, boolean loginStatus) {
        this.id = id;
        this.telephone = telephone;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.loginStatus = loginStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
