package com.theSunAndSnow.repository;

public interface OrderRepository {
    public void addOrder(Integer customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, String boughtTime);
}
