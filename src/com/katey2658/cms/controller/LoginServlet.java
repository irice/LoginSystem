package com.katey2658.cms.controller;

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
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("----------------------doGet");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=gbk");
        PrintWriter out = response.getWriter();

        //获取session对象
        HttpSession session = request.getSession();
        //获取application对象
        ServletContext application=getServletContext();

        out.print("<link href=\"/bootstrap-4.0.0-alpha.5-dist/css/bootstrap.css\" rel=\"stylesheet\">\n" +
                "    <!-- CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/reset.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/supersized.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/style.css\">");

        out.print("<div class=\"page-container\" >");
        out.print("    <h1 class=\"form-signin-heading\">LOGIN</h1>");
        out.print("    <form action=\"loginI\" method=\"post\">");
        out.print("        <input type=\"text\" name=\"userName\"  class=\"form-control\"  placeholder=\"请输入您的用户名\"/><br>");
        out.print("        <input type=\"password\" name=\"userPwd\"  class=\"form-control\" placeholder=\"请输入您的用户密码！\"/><br>");
        out.print("        <input type=\"submit\" class=\"btn btn-lg btn-primary btn-block\" value=\"登录\">");
        out.print("        <a href=\"register.jsp\"><input type=\"button\" class=\"btn btn-lg btn-warning btn-block \" value=\"注册\"></a>");
        out.print("    </form>");
        out.print("</div>");


        out.print("<script src=\"bootstrap-4.0.0-alpha.5-dist/js/bootstrap.js\"/>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
