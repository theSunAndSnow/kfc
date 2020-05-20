package com.theSunAndSnow.repository.impl;

import com.theSunAndSnow.entity.Order;
import com.theSunAndSnow.repository.OrderRepository;
import com.theSunAndSnow.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void addOrder(Integer customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, String boughtTime) {
        Double totalPayment = chickenWing * Order.CHICKEN_WING_PRICE
                              + chickenWingSetMeal * Order.CHICKEN_WING_SET_MEAL_PRICE
                              + beer * Order.BEER_PRICE
                              + hamburger * Order.HAMBURGER_PRICE
                              + congee * Order.CONGEE_PRICE
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
}
