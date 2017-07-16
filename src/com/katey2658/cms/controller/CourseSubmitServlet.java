package com.katey2658.cms.controller;

import com.katey2658.cms.entity.Course;
import com.katey2658.cms.service.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**
 * Created by 11456 on 2016/11/21.
 */
public class CourseSubmitServlet extends HttpServlet{

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

        Course course=new Course();
        UserFactory uf=new UserFactory();

        String courseId=request.getParameter("courseId");
        String courseName=request.getParameter("courseName");
        String courseHours=request.getParameter("courseHours");
        String courseTeacher=request.getParameter("courseTeacher");
        String courseCredit=request.getParameter("courseCredit");
        String isNeedCourseMatrial=request.getParameter("needMaterial");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        if (courseId!=null&&
                courseName!=null&&
                courseHours!=null&&
                courseCredit!=null&&
                courseCredit!=null&&
                isNeedCourseMatrial!=null&&
                startTime!=null&&
                endTime!=null){
            course.setCourseId(courseId);
            course.setCourseName(courseName);
            course.setCourseHours(Integer.valueOf(courseHours));
            course.setCourseTeacher(courseTeacher);
            course.setCourseCredit(Float.valueOf(courseCredit));
            course.setNeedMaterial(isNeedCourseMatrial.equals("true")? true:false);
            course.setStartTime(Date.valueOf(startTime));
            course.setEndTime(Date.valueOf(endTime));

            if(uf.getUserService().updateCourse(course)){
                System.out.println("=================修改成功");
            }else if(uf.getUserService().addCourse(course)){
                System.out.println("=================添加成功");
            }

        }
        response.sendRedirect("CourseAll");
    }
}
