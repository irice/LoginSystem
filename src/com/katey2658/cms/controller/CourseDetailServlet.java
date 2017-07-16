package com.katey2658.cms.controller;

import com.katey2658.cms.entity.Course;
import com.katey2658.cms.service.UserFactory;

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
public class CourseDetailServlet extends HttpServlet {

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
            response.sendRedirect("loginCheck");
        }

        UserFactory uf=new UserFactory();
        String courseId=request.getParameter("id");
        String editable=request.getParameter("editable");
        if (editable.equals("true")){
            editable="";
        }else if (editable.equals("false")){
            editable="readonly=\"readonly\"";
        }
        Course course=null;
        if(!courseId.isEmpty()){
            course=uf.getUserService().courseDetail(courseId);
        }


        out.print("<form action=\"courseSubmit\" method=\"post\">\n" +
                "    <div >\n" +
                "        课程编号:\n" +
                "        <input type=\"text\" name=\"courseId\" readonly=\"readonly\" value=\""+course.getCourseId()+"\"/><br>\n" +
                "        课程名字:\n" +
                "        <input type=\"text\" name=\"courseName\" "+editable+" value=\""+course.getCourseName()+"\" /><br>\n" +
                "        课时:\n" +
                "        <input type=\"text\" name=\"courseHours\" "+editable+" value=\""+course.getCourseHours()+"\" /><br>\n" +
                "        教师:\n" +
                "        <input type=\"text\" name=\"courseTeacher\" "+editable+" value=\""+course.getCourseTeacher()+"\"/><br>\n" +
                "        学分:\n" +
                "        <input type=\"text\" name=\"courseCredit\" "+editable+" value=\""+course.getCourseCredit()+"\"/><br>\n" +
                "        是否需要教材:\n" +
                "        <input type=\"text\" name=\"needMaterial\" "+editable+" value=\""+course.isNeedMaterial()+"\"/><br>\n" +
                "        开始时间:\n" +
                "        <input type=\"date\" name=\"startTime\" "+editable+ " value=\""+course.getStartTime()+"\"/><br>\n" +
                "        结束时间\n" +
                "        <input type=\"date\" name=\"endTime\" "+editable+ " value=\""+course.getEndTime()+"\"/><br>\n" +
                "        <input type=\"submit\" value=\"保存\"/><br>\n" +
                "    </div>\n" +
                "</form>\n" +
                "\n" +
                "<a href=\"home\">首页:</a>\n" +
                "<a href=\"CourseAll\">:课程:</a>\n" +
                "<a href=\"page1\">页面1</a>");

    }
}
