package com.theSunAndSnow.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.theSunAndSnow.entity.Customer;
import com.theSunAndSnow.service.BuyService;
import com.theSunAndSnow.service.Impl.BuyServiceImpl;

@WebServlet("/buyServlet")
public class BuyServlet extends HttpServlet {

    BuyService buyService = new BuyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Integer chickenWing = Integer.parseInt( (String)req.getParameter("chickenWing") );
        Integer chickenWingSetMeal = Integer.parseInt( (String)req.getParameter("chickenWingSetMeal") );
        Integer beer = Integer.parseInt( (String)req.getParameter("beer") );
        Integer hamburger = Integer.parseInt( (String)req.getParameter("hamburger") );
        Integer congee = Integer.parseInt( (String)req.getParameter("congee") );
        Integer cola = Integer.parseInt( (String)req.getParameter("cola") );
        Boolean coupon = Boolean.parseBoolean(req.getParameter("coupon"));

        HttpSession session = req.getSession();
        Integer customerId = ((Customer)session.getAttribute("customer")).getId();

        buyService.customersBuy(customerId, chickenWing, chickenWingSetMeal, beer, hamburger, congee, cola, coupon);

        System.out.println("/buyServlet 链接成功!");
    }
}
