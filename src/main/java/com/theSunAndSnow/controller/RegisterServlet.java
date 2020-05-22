package com.theSunAndSnow.controller;

import com.google.gson.Gson;
import com.theSunAndSnow.entity.Customer;
import com.theSunAndSnow.entity.RegisterStatus;
import com.theSunAndSnow.service.Impl.RegisterServiceImpl;
import com.theSunAndSnow.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthScrollBarUI;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    private RegisterService registerService = new RegisterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephone");
        System.out.println(name + sex + password + " " + telephone);

        Customer customer = (Customer)registerService.register(name, sex, telephone, password);
        Gson gson = new Gson();
        RegisterStatus registerStatus = new RegisterStatus(false);

        if (customer == null) {
            resp.getWriter().write(gson.toJson(registerStatus));
//            resp.sendRedirect("login.html");
        } else {
            registerStatus.setStatus(true);
            resp.getWriter().write(gson.toJson(registerStatus));
        }
    }
}
