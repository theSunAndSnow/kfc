package com.theSunAndSnow.entity;

import java.util.Calendar;
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

//    快餐价格
    public static double CHICKEN_WING_PRICE = 12.0;
    public static double CHICKEN_WING_SET_MEAL_PRICE = 72.0;
    public static double BEER_PRICE = 15.0;
    public static double HAMBURGER_PRICE = 20.0;
    public static double CONGEE_PRICE = 10.0;
    public static double COLA_PRICE = 11.0;

//    各个打折时间段
    public static Calendar BREAKFAST_Begin = Calendar.getInstance();
    public static Calendar BREAKFAST_End = Calendar.getInstance();
    public static Calendar DINNER_Begin = Calendar.getInstance();
    public static Calendar DINNER_End = Calendar.getInstance();
    public static Calendar NIGHTSNACK_Begin = Calendar.getInstance();
    public static Calendar NIGHTSNACK_End = Calendar.getInstance();

    static  {
//        设置打折时间段的具体时间
        BREAKFAST_Begin.set(Calendar.HOUR_OF_DAY, 5);
        BREAKFAST_Begin.set(Calendar.MINUTE, 45);
        BREAKFAST_Begin.set(Calendar.MILLISECOND, 0);

        BREAKFAST_End.set(Calendar.HOUR_OF_DAY, 9);
        BREAKFAST_End.set(Calendar.MINUTE, 14);
        BREAKFAST_End.set(Calendar.MILLISECOND, 0);

        DINNER_Begin.set(Calendar.HOUR_OF_DAY, 9);
        DINNER_Begin.set(Calendar.MINUTE, 15);
        DINNER_Begin.set(Calendar.MILLISECOND, 0);

        DINNER_End.set(Calendar.HOUR_OF_DAY, 22);
        DINNER_End.set(Calendar.MINUTE, 44);
        DINNER_End.set(Calendar.MILLISECOND, 0);

        NIGHTSNACK_Begin.set(Calendar.HOUR_OF_DAY, 11);
        NIGHTSNACK_Begin.set(Calendar.MINUTE, 0);
        NIGHTSNACK_Begin.set(Calendar.MILLISECOND, 0);

        NIGHTSNACK_End.set(Calendar.HOUR_OF_DAY, 12);
        NIGHTSNACK_End.set(Calendar.MINUTE, 0);
        NIGHTSNACK_End.set(Calendar.MILLISECOND, 0);
    }

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
