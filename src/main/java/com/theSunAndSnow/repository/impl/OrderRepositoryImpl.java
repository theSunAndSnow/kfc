package com.theSunAndSnow.repository.impl;

import com.theSunAndSnow.entity.Order;
import com.theSunAndSnow.repository.OrderRepository;
import com.theSunAndSnow.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void addOrder(Integer customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, String boughtTime, String discountItem) {
        int chickenWingDiscount = 0, congeeDiscount = 0, chcikenWingSetMealDiscount = 0;

        switch (discountItem) {
            case "chickenWing" :
                chickenWingDiscount = 1;
                break;

            case "congee" :
                congeeDiscount = 1;
                break;

            case "chickenWingSetMeal" :
                chcikenWingSetMealDiscount = 1;
                break;
        }

//        根据 discountItem 来确定打折后的商品价格
        Double totalPayment = chickenWing * ( Order.CHICKEN_WING_PRICE * (1 - 0.2 * chickenWingDiscount) )
                              + chickenWingSetMeal * ( Order.CHICKEN_WING_SET_MEAL_PRICE * (1 - 0.2 * chcikenWingSetMealDiscount) )
                              + beer * Order.BEER_PRICE
                              + hamburger * Order.HAMBURGER_PRICE
                              + congee * ( Order.CONGEE_PRICE * (1 - 0.2 * congeeDiscount) )
                              + cola * Order.COLA_PRICE;

        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into `order`(customerid, chickenWing, chickenWingSetMeal, beer, hamburger, congee, cola, totalPayment, boughttime)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, chickenWing);
            preparedStatement.setInt(3, chickenWingSetMeal);
            preparedStatement.setInt(4, beer);
            preparedStatement.setInt(5, hamburger);
            preparedStatement.setInt(6, congee);
            preparedStatement.setInt(7, cola);
            preparedStatement.setDouble(8, totalPayment);
            preparedStatement.setString(9, boughtTime);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }

//    public static void main(String[] args) {
//        OrderRepository orderRepository = new OrderRepositoryImpl();
//        orderRepository.addOrder(1, 1, 0, 0, 0, 0, 0, "2020-5-26 17:19:00", "chickenWing");
//    }
}
