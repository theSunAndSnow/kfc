package com.theSunAndSnow.controller;

import com.google.gson.Gson;
import com.theSunAndSnow.entity.LoginStatus;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        LoginStatus loginStatus = new LoginStatus(true);
        resp.getWriter().write(gson.toJson(loginStatus));
        if (req.getParameter("name") != null) {
            resp.getWriter().write("success");
            try {
                Thread.sleep(3000);
                resp.sendRedirect("index.html");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        LoginStatus loginStatus = new LoginStatus(true);
        resp.getWriter().write(gson.toJson(loginStatus));
        if (req.getParameter("name") != null) {
            resp.getWriter().write("success");
            try {
                Thread.sleep(3000);
                resp.sendRedirect("index.html");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
