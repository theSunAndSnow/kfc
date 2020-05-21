package com.theSunAndSnow.repository.impl;

import com.theSunAndSnow.entity.Customer;
import com.theSunAndSnow.repository.CustomerRepository;
import com.theSunAndSnow.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.spi.CurrencyNameProvider;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Object login(String telephone, String password) {
        Customer customer = null;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select *\n" +
                "from customer\n" +
                "where telephone = ?\n" +
                "and password = ?";
        try {
            customer = queryRunner.query( connection, sql, new BeanHandler<>(Customer.class), telephone, password );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return customer;
    }
}
