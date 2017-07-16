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
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by 11456 on 2016/11/21.
 */
public class HomeServlet extends HttpServlet {

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

        //如果没有登陆，应该跳去登陆
        if(session.getAttribute("user")==null){
            response.sendRedirect("loginCheck");
        }



        long time=session.getCreationTime();
        Date currentTime=new Date();
        long times=currentTime.getTime();
        long seconds=(times-time)/1000;
        long leftSeconds=seconds%(60*60);
        if(leftSeconds==0){
            out.print("<script>alert('您已经连续浏览网站"+seconds/(60*60)+"小时了，休息一下吧') </script>");
        }

        out.print("<h3>你好！！"+((User)session.getAttribute("user")).getUserName()+"</h3>");

        int count=0;
        Enumeration en=application.getAttributeNames();
        if(en!=null){
            String name=null;
            String value=null;
            while (en.hasMoreElements()) {
                name=(String)en.nextElement();
                value=application.getAttribute(name).toString();
                if(name.equals(value)){
                    count++;
                    out.println("<li>");
                    out.println(value);
                    out.println("</li>");
                }
            }
        }

        out.print("</ul>");
        out.print("<h4>登陆总人数为 "+count+"</h4>");
        out.print("<a href=\"home\">首页:</a>");
        out.print("<a href=\"CourseAll\">:课程:</a>");
        out.print("<a href=\"page1\">页面1</a>");

    }
}
