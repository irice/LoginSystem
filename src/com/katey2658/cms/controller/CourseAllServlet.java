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
import java.util.Vector;

/**
 * Created by 11456 on 2016/11/21.
 */
public class CourseAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("gbk");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        //response.setHeader("Content-Type","text/html; charset=gbk");
        PrintWriter out = response.getWriter();

        //获取session对象
        HttpSession session = request.getSession();
        //获取application对象
        ServletContext application=getServletContext();

        UserFactory uf=new UserFactory();


        out.print("   <link href=\"/bootstrap-4.0.0-alpha.5-dist/css/bootstrap.css\" rel=\"stylesheet\">\n" +
                "\n"+
                "\n" +
                "\n" +
                "<div class=\"container-fluid\">\n" +
                "    <!-- Content here -->\n" +
                "\n" +
                "    <nav class=\"navbar navbar-dark bg-primary\">\n" +
                "        <!-- Navbar content -->\n" +
                "        <a href=\"home\" ><button type=\"button\" class=\"btn btn-primary\">HOME</button></a>\n" +
                "    </nav>");


        if(session.getAttribute("user")==null){
            session.setAttribute("lastPath","CourseAll");
            response.sendRedirect("loginCheck");
        }

        //删除一条记录
        String deleteId=request.getParameter("deleteId");
        if((deleteId!=null)&&uf.getUserService().deleteCourse(deleteId)){
            // out.print("<script>alert('删除成功！') </script>");
        }
        //删除多条记录
        String[] deleteAll=request.getParameterValues("checkAll");
        if (deleteAll!=null){
            for (String delId:deleteAll){
                if (!delId.isEmpty())
                    uf.getUserService().deleteCourse(delId);
            }
            // out.print("<script>alert('删除成功！') </script>");
        }

        //获取当前页数
        int pageNum=1;
        String currentPage=request.getParameter("currentPage");
        if (currentPage!=null){
            pageNum=Integer.valueOf(currentPage);
            if (pageNum<=0){
                pageNum=1;
            }
        }

        out.print(" <div>\n" +
                "        <form action=\"CourseAll\" method=\"post\">\n" +
                "            <table class=\"table table-striped\">");

        //整张表的表头
        out.print("<tr>");
        out.print("<th><input type='checkbox' id='selAll' name='deleteItem' onclick='selectAll();'>全选</th>");
        out.print("<th>课程编号</th>");
        out.print("<th>课程名字</th>");
        out.print("<th>课时</th>");
        out.print("<th>教师</th>");
        out.print("<th>学分</th>");
        out.print("<th>是否需要教材</th>");
        out.print("<th>开始时间</th>");
        out.print("<th>结束时间</th>");
        out.print("<th>详细</th>");
        out.print("<th>修改</th>");
        out.print("<th>删除</th>");
        out.print("</tr>");

        //表体
        Vector<Course> courseVector=uf.getUserService().getpage(pageNum);
        if (courseVector!=null){
            for (Course course:courseVector){
                out.print("<tr>");
                out.print("<td><input type='checkbox' name='checkAll' id='checkAll' value='"+course.getCourseId()+"' onclick='setSelectAll();'></td>");
                out.print("<td>"+course.getCourseId()+"</td>");
                out.print("<td>"+course.getCourseName()+"</td>");
                out.print("<td>"+course.getCourseHours()+"</td>");
                out.print("<td>"+course.getCourseTeacher()+"</td>");
                out.print("<td>"+course.getCourseCredit()+"</td>");
                out.print("<td>"+course.isNeedMaterial()+"</td>");
                out.print("<td>"+course.getStartTime()+"</td>");
                out.print("<td>"+course.getEndTime()+"</td>");
                out.print("<td><a href=\"CourseDetail?editable=false&id="+course.getCourseId()+"\">详细</a></td>");
                out.print("<td><a href=\"CourseDetail?editable=true&id="+course.getCourseId()+"\">修改</a></td>");
                out.print("<td><a href=\"CourseAll?deleteId="+course.getCourseId()+"\">" +
                        "<button type=\"button\" class=\"btn btn-danger\">删除</button>\n</a></td>");
                out.print("</tr>");
            }
        }

        out.print("</table>\n" +
                "            <input type=\"submit\" class=\"btn btn-danger\" value=\"确定删除\">\n" +
                "        </form>\n" +
                "        <a class=\"btn btn-primary\" href=\"addCourse\">增加记录</a>\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "    <div aria-label=\"...\">\n" +
                "        <ul class=\"pagination\">\n" +
                "            <li class=\"page-item disabled\">\n" +
                "                <a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-label=\"Previous\">\n" +
                "                    <span aria-hidden=\"true\">&laquo;</span>\n" +
                "                    <span class=\"sr-only\">Previous</span>\n" +
                "                </a>\n" +
                "            </li>");
        //总页数
        int pageCountSum=uf.getUserService().getPageSumCount();
        //页码导航条显示个数
        int pageItem=5;
        if (pageCountSum/5==0){
            pageItem=pageCountSum;
        }else if((pageCountSum-pageNum)<=2&&(pageCountSum-pageNum)>=0){
            pageItem=3+pageCountSum-pageNum;
        }
        //分页导航栏，页码,，显示
        for (int i=1;i<=pageItem;i++){
            if (pageNum<=3){
                if (pageNum==i){
                    out.print("<li class=\"page-item active\">");
                }
                out.print(" <a class=\"page-link\" href=\"CourseAll?currentPage="+(i)+"\">"+(i)+"</a></li>");
            }else {
                if (i==3){
                    out.print("<li class=\"page-item active\">");
                }else{
                    out.print("<li class=\"page-item\">");
                }
                out.print(" <a class=\"page-link\" href=\"CourseAll?currentPage="+(pageNum-3+i)+"\">"+(pageNum-3+i)+"</a></li>");
            }
        }

        out.print("<li class=\"page-item\">\n" +
                "                <a class=\"page-link\" href=\"#\" aria-label=\"Next\">\n" +
                "                    <span aria-hidden=\"true\">&raquo;</span>\n" +
                "                    <span class=\"sr-only\">Next</span>\n" +
                "                </a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<a href=\"home\">首页:</a>\n" +
                "<a href=\"CourseAll\">:课程:</a>\n" +
                "<a href=\"page1\">页面1</a>");
        out.print("\n" +
                "<script src=\"assets/js/checkall.js\"></script>\n" +
                "<script src=\"bootstrap-4.0.0-alpha.5-dist/js/bootstrap.js\"/>\n" );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
