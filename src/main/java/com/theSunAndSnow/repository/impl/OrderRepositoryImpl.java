package com.theSunAndSnow.repository.impl;

import com.theSunAndSnow.entity.Order;
import com.theSunAndSnow.repository.OrderRepository;
import com.theSunAndSnow.utils.JDBCTools;
import org.omg.PortableInterceptor.SUCCESSFUL;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class OrderRepositoryImpl implements OrderRepository {
    /**
     * 向数据库中添加客户的账单记录
     * @param customerId
     * @param chickenWing
     * @param chickenWingSetMeal
     * @param beer
     * @param hamburger
     * @param congee
     * @param cola
     * @param boughtTime
     * @param discountItem
     */
    @Override
    public void addOrder(Integer customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, String boughtTime, String discountItem, Boolean coupon) {

        Double totalPayment = getTotalPayment(customerId, chickenWing, chickenWingSetMeal, beer, hamburger, congee, cola, discountItem, coupon);

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

            reduceCouponNum(customerId, connection, preparedStatement); // 减少此用户优惠券的数量

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }

        printReceipt(customerId, chickenWing, chickenWingSetMeal, beer, hamburger, congee, cola, boughtTime, discountItem, coupon);
    }

    /**
     * 模拟打印小票的功能，将小票信息打印到文件中
     * @param customerId
     * @param chickenWing
     * @param chickenWingSetMeal
     * @param beer
     * @param hamburger
     * @param congee
     * @param cola
     * @param boughtTime
     * @param discountItem
     */
    private void printReceipt(Integer customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, String boughtTime, String discountItem, Boolean coupon) {

//        从数据库获取用户姓名
        String customerName = getCustomerName(customerId);

        double totalPayment = getTotalPayment(customerId, chickenWing, chickenWingSetMeal, beer, hamburger, congee, cola, discountItem, coupon);
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
        double chickenWingPayment = chickenWing * ( Order.CHICKEN_WING_PRICE * (1 - 0.2 * chickenWingDiscount) ),
                chickenWingSetMealPayment =  chickenWingSetMeal * ( Order.CHICKEN_WING_SET_MEAL_PRICE * (1 - 0.2 * chcikenWingSetMealDiscount) ),
                beerPayment =  beer * Order.BEER_PRICE,
                hamburgerPayment = hamburger * Order.HAMBURGER_PRICE,
                congeePayment = congee * ( Order.CONGEE_PRICE * (1 - 0.2 * congeeDiscount) ),
                colaPayment = cola * Order.COLA_PRICE;

//        若不存在 receipts 文件夹，则在此工程所使用的Tomcat路径下的bin文件夹中新建此文件夹
        File dir = new File("/receipts");
        dir.mkdir(); // 在 Tomcat 路径的bin目录下创建一个 receipts 文件夹

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR),
                month = calendar.get(Calendar.MONTH) + 1,
                day = calendar.get(Calendar.DAY_OF_MONTH),
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                second = calendar.get(Calendar.SECOND);

        String fileTime = year + "-" + month + "-" + day + " " + hour + "-" + minute + "-" + second;

        // 创建一个购买日期和客户姓名相关的文件对象
        File file = new File("receipts/" + fileTime + customerName + ".txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write("*********************订单*********************" + "\n");
            writer.write("客户姓名：" + customerName + "\t" + "客户ID：" + customerId + "\n");
            writer.write("********************************************" + "\n");
            writer.write("香辣鸡翅：" + chickenWing + "份 " + String.format("%.1f", chickenWingPayment) + "元" + "\n");
            writer.write("炸鸡啤酒套餐：" + chickenWingSetMeal + "份 " + String.format("%.1f", chickenWingSetMealPayment) + "元" + "\n");
            writer.write("啤酒：" + beer + "瓶 " + String.format("%.1f", beerPayment) + "元" + "\n");
            writer.write("香辣鸡腿汉堡" + hamburger + "份 " + String.format("%.1f", hamburgerPayment) + "元" + "\n");
            writer.write("冬菇滑鸡粥：" + congee + "份 " + String.format("%.1f", congeePayment) + "元" + "\n");
            writer.write("百事可乐：" + cola + "瓶 " + String.format("%.1f", colaPayment) + "元" + "\n");
            writer.write("                                        合计：" + String.format("%.1f", totalPayment) + "元");
            writer.write("                                        " + boughtTime);
            writer.write( "\n********************************************" + "\n");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回客户付款金额
     * @param customerId
     * @param chickenWing
     * @param chickenWingSetMeal
     * @param beer
     * @param hamburger
     * @param congee
     * @param cola
     * @param discountItem
     * @return
     */
    private Double getTotalPayment(Integer customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola, String discountItem, Boolean coupon) {
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

//        使用优惠券可以打九折
        if (coupon) {
            totalPayment *= 0.9;
        }

        return totalPayment;
    }

    /**
     * 根据客户id，返回客户姓名
     * @param customerId
     * @return
     */
    private String getCustomerName(Integer customerId) {
        String customerName = null;
        Connection connection = JDBCTools.getConnection();
        String sql = "select customer.name\n" +
                "from customer\n" +
                "where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerName = resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return customerName;
    }

    private void reduceCouponNum(Integer customerId, Connection connection, PreparedStatement preparedStatement) {
        String sql = "update  coupon\n" +
                "set couponNum = couponNum - 1\n" +
                "where customerId = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
