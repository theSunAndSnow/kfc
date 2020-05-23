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

    @Override
    public Object register(String name, String sex, String telephone, String password) {
        Customer customer = null;

        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from customer where telephone = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, telephone);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) { // 验证账号是否已存在,若不存在，则注册
                sql = "insert into customer (telephone, `name`, sex, password) VALUES\n" +
                        "(?, ?, ?, ?);";

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, telephone);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, sex);
                preparedStatement.setString(4, password);

                preparedStatement.executeUpdate(); // 向数据库插入数据

                sql = "select id from customer where telephone = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, telephone);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1); // 获得新账号在数据库中的id
                    customer = new Customer(id, telephone, password, name, sex, false);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("出现异常了！");
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return customer;
    }
}
