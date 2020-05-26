package com.theSunAndSnow.service.Impl;

import com.google.gson.Gson;
import com.theSunAndSnow.entity.Order;
import com.theSunAndSnow.repository.OrderRepository;
import com.theSunAndSnow.repository.impl.OrderRepositoryImpl;
import com.theSunAndSnow.service.BuyService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BuyServiceImpl implements BuyService {

    private OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public void customersBuy(Integer customerId, Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola) {
        Date date = new Date(); // 获取当前客户购买时间
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置工具类 simpleDateFormat 要转换成的格式
        String boughtTime = simpleDateFormat.format(date); // 将 date 时间格式转化为数据库 datetime 变量的格式，并且得到字符串形式
        String discount = null; // 打折食物名称

        if (calendar.after(Order.BREAKFAST_Begin) && calendar.before(Order.BREAKFAST_End)) {
            discount = "congee";
        } else if ( calendar.after(Order.DINNER_Begin) && calendar.before(Order.DINNER_End)) {
            discount = "chickenWing";
        } else if ( calendar.after(Order.NIGHTSNACK_Begin) && calendar.before(Order.NIGHTSNACK_End)) {
            discount = "chickenWingSetMeal";
        }

//        System.out.println(discount);
//        System.out.println(boughtTime);
        orderRepository.addOrder(customerId, chickenWing, chickenWingSetMeal, beer, hamburger, congee, cola, boughtTime, discount);
    }

    /**
     * 测试代码
     * @param args
     */
    public static void main(String[] args) {
        BuyService buyService = new BuyServiceImpl();
        buyService.customersBuy(1, 1, 1,1,1,1, 1);
    }
}
