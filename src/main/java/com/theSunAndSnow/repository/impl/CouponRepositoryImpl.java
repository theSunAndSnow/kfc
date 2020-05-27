package com.theSunAndSnow.repository.impl;


import com.theSunAndSnow.repository.CouponRepository;
import com.theSunAndSnow.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CouponRepositoryImpl implements CouponRepository {
    @Override
    public Integer getCouponNum(Integer customerId) {
        Integer couponNum = null;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select couponNum\n" +
                "from coupon\n" +
                "where customerId = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                couponNum = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return couponNum;
    }
}
