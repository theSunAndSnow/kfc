package com.theSunAndSnow.controller;


import com.google.gson.Gson;
import com.theSunAndSnow.entity.Customer;
import com.theSunAndSnow.entity.LoginStatus;
import com.theSunAndSnow.service.Impl.LoginServiceImpl;
import com.theSunAndSnow.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        System.out.println(username + "+" + password);

        String telephone = username; // 用户名就是用户电话号码
        Gson gson = new Gson();

        Customer customer = (Customer)loginService.login(telephone, password);
        LoginStatus loginStatus = new LoginStatus();
        if (customer == null) { // 如果登陆失败
            loginStatus.setStatus(false);
            System.out.println("登陆失败！");
            resp.sendRedirect("login.html");
        } else {
            System.out.println("登陆成功！");
            loginStatus.setStatus(true);
//            将用户名存入 session 中，方便 order.html 页面获取用户信息
            session.setAttribute("telephone", telephone);
            session.setAttribute("password", password);
            session.setAttribute("customer", customer);
            req.getRequestDispatcher("order.html").forward(req, resp);
//            resp.sendRedirect("order.html");

//            resp.getWriter().write(gson.toJson(loginStatus));
//            resp.getWriter().write(gson.toJson(customer));
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String method = req.getParameter("method");
            if (method == null) {
                System.out.println("method is null");
                method = "status";
            }

            HttpSession session = req.getSession();
            Gson gson = new Gson();
            LoginStatus loginStatus = new LoginStatus();

            switch (method) {
                case "status" :
//                    从 session 中获得账号信息，若结果为空，则说明未登陆
                    String telephone = (String)session.getAttribute("telephone");
                    String password = (String)session.getAttribute("password");

                    if (telephone == null || password == null) {
                        loginStatus.setStatus(false);
                        resp.getWriter().write(gson.toJson(loginStatus));
                    } else {
                        loginStatus.setStatus(true);
//                        resp.getWriter().write(gson.toJson(loginStatus));
                        List<Object> list = new ArrayList<>();
                        list.add(loginStatus);
                        Customer customer = (Customer) session.getAttribute("customer");
                        list.add(customer);
                        String listStr = gson.toJson(list);
                        listStr = new String(listStr.getBytes("ISO-8859-1"), "utf-8");

                        resp.setContentType("text/html;charset=UTF-8");
                        resp.getWriter().write(listStr);
                        System.out.println(list);
                    }
                    break;

                case "queryCoupon" :
                    Customer customer = (Customer)session.getAttribute("customer");
                    Integer couponNum = loginService.getCouponNum(customer.getId());
                    String couponNumJson = "{\"couponNum\" : " + couponNum + "}"; // 以json格式传递数据
                    resp.getWriter().write(couponNumJson);
                    break;
            }
    }

}
