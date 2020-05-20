package com.theSunAndSnow.entity;

import java.util.Date;

public class Order {
    private Integer id;
    private String customerId;
    private Integer chickenWing;
    private Integer chickenWingSetMeal;
    private Integer beer;
    private Integer hamburger;
    private Integer congee;
    private Integer cola;
    private Double totalPayment;
    private Date time;

    public static double CHICKEN_WING_PRICE = 12.0;
    public static double CHICKEN_WING_SET_MEAL_PRICE = 72.0;
    public static double BEER_PRICE = 15.0;
    public static double HAMBURGER_PRICE = 20.0;
    public static double CONGEE_PRICE = 10.0;
    public static double COLA_PRICE = 11.0;

    public Order() {
    }

    public Order(String customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, Double totalPayment) {
        this.customerId = customerId;
        this.chickenWing = chickenWing;
        this.chickenWingSetMeal = chickenWingSetMeal;
        this.beer = beer;
        this.hamburger = hamburger;
        this.congee = congee;
        this.cola = cola;
        this.totalPayment = totalPayment;
    }

    public Order(Integer id, String customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, Double totalPayment, Date time) {
        this.id = id;
        this.customerId = customerId;
        this.chickenWing = chickenWing;
        this.chickenWingSetMeal = chickenWingSetMeal;
        this.beer = beer;
        this.hamburger = hamburger;
        this.congee = congee;
        this.cola = cola;
        this.totalPayment = totalPayment;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getChickenWing() {
        return chickenWing;
    }

    public void setChickenWing(Integer chickenWing) {
        this.chickenWing = chickenWing;
    }

    public Integer getChickenWingSetMeal() {
        return chickenWingSetMeal;
    }

    public void setChickenWingSetMeal(Integer chickenWingSetMeal) {
        this.chickenWingSetMeal = chickenWingSetMeal;
    }

    public Integer getBeer() {
        return beer;
    }

    public void setBeer(Integer beer) {
        this.beer = beer;
    }

    public Integer getHamburger() {
        return hamburger;
    }

    public void setHamburger(Integer hamburger) {
        this.hamburger = hamburger;
    }

    public Integer getCongee() {
        return congee;
    }

    public void setCongee(Integer congee) {
        this.congee = congee;
    }

    public Integer getCola() {
        return cola;
    }

    public void setCola(Integer cola) {
        this.cola = cola;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
