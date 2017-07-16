package com.katey2658.cms.controller;

import com.katey2658.cms.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 11456 on 2016/11/21.
 */
public class Page1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();


        //获取session对象
        HttpSession session = request.getSession();
        //获取application对象
        ServletContext application=getServletContext();

        if(session.getAttribute("user")==null){
            session.setAttribute("lastPath","page1");
            response.sendRedirect("loginCheck");
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");


        out.print("<h3>page1 你好！！"+((User)session.getAttribute("user")).getUserName()+"</h3>\n");
        out.print("<a href=\"home\">首页:</a>");
        out.print("<a href=\"CourseAll\">:课程:</a>");
        out.print("<a href=\"page1\">页面1</a> ");

    }
}
