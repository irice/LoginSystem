package com.katey2658.cms.controller;

import com.katey2658.cms.common.DBUtilServlet;
import com.katey2658.cms.entity.User;
import com.katey2658.cms.service.UserFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by 11456 on 2016/11/21.
 */
public class doCheckLoginServlet  extends DBUtilServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        //获取session对象
        HttpSession session = request.getSession();
        //获取application对象
        ServletContext application=getServletContext();

        //判断cookie是否有自动登陆功能以及检验cookie里面账号有效性
        String userName="";
        String userPwd="";
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie: cookies){
            if (("userName").equals(cookie.getName())){
                userName=cookie.getValue();
            }else if (("userPwd").equals(cookie.getName())){
                userPwd=cookie.getValue();
            }
        }

        if (!(userName.isEmpty()||userPwd.isEmpty())) {
            //验证账号有效性
            User user=new User(userName,userPwd);
            UserFactory userFactory = new UserFactory();
            if (loginCheck(user.getUserName(),user.getUserPwd())==0) {
                session.setAttribute("user", user);
                //账号存在，跳转到首页或者之前浏览的页面
                Object lastPathObj=session.getAttribute("lastPath");
                if (lastPathObj==null){
                    response.sendRedirect("home");
                }else{
                    response.sendRedirect(lastPathObj.toString());
                }
            }
        }


        //System.out.print(application.getInitParameter("username"));
        //request.getRequestDispatcher("login.jsp").forward(request,response);
        response.sendRedirect("login");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    /**
     * 检验登录信息
     * @param userName 姓名
     * @param userPwd 密码
     * @return -2是没有用户名，-1 密码错误，0成功
     */
    public  int loginCheck(String userName,String userPwd){
        String passwd=null;

        int loginCode=-2;
        try {
            sql="select username,passpwd from user where username='"+userName+"'";
            rs=st.executeQuery(sql);

            if (rs.next()) {
                passwd = rs.getString(2);
                if (passwd.equals(userPwd)) {
                    loginCode = 0;
                } else {
                    loginCode=-1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //检验***
        System.out.println("::"+loginCode);
        return  loginCode;
    }
}
