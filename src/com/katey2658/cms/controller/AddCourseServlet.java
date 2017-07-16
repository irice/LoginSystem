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
public class AddCourseServlet extends HttpServlet{

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
            session.setAttribute("lastPath","addCourse");
            response.sendRedirect("loginCheck");
        }

        out.print("<form action=\"courseSubmit\" method=\"post\">\n" +
                "    <div >\n" +
                "            课程编号:\n" +
                "            <input type=\"text\" name=\"courseId\" ><br>\n" +
                "            课程名字:\n" +
                "            <input type=\"text\" name=\"courseName\" ><br>\n" +
                "            课时:\n" +
                "            <input type=\"text\" name=\"courseHours\" ><br>\n" +
                "            教师:\n" +
                "            <input type=\"text\" name=\"courseTeacher\"><br>\n" +
                "            学分:\n" +
                "            <input type=\"text\" name=\"courseCredit\" ><br>\n" +
                "            是否需要教材:\n" +
                "            <input type=\"text\" name=\"needMaterial\" ><br>\n" +
                "            开始时间:\n" +
                "            <input type=\"date\" name=\"startTime\" ><br>\n" +
                "            结束时间:\n" +
                "            <input type=\"date\" name=\"endTime\" ><br>\n" +
                "            <input type=\"submit\" value=\"保存\"><br>\n" +
                "    </div>\n" +
                "\n" +
                "</form>"+
                "<a href=\"home\">首页:</a>\n" +
                "<a href=\"CourseAll\">:课程:</a>\n" +
                "<a href=\"page1\">页面1</a>");

    }
}
