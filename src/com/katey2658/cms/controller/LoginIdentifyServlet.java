package com.katey2658.cms.controller;

import com.katey2658.cms.common.DBUtilServlet;
import com.katey2658.cms.entity.User;

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
public class LoginIdentifyServlet extends DBUtilServlet{

    //执行Get方法提交的数据
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //执行Post方法提交的数据
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        //先确定数据库已经连接,没有的话就需要重新连接
        if (isDBOpen() == false) {
            reConnect();
        }
        //获取session对象
        HttpSession session = request.getSession();
        //获取application对象
        ServletContext application=getServletContext();

        //获取输入请求用户名和密码
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
//检验
        System.out.println(userName+"::"+userPwd);

        //如果用户名非空就应该提示输入有空值就该返回提示
        if ((userName == null || userPwd == null) || (userName.isEmpty() || userPwd.isEmpty())) {
            //跳转到错误提示页面然后回到登录页面（）
            out.println("账号或密码不能为空！请重新登陆");
            response.setHeader("refresh", "5;url=login");
        } else {
            //构造一个对象
            User user = new User();
            user.setUserName(userName);
            user.setUserPwd(userPwd);

            //登陆校验返回码
            int loginCode = -2;

            //校验是否正常登陆
            if ((loginCode = loginCheck(user.getUserName(), user.getUserPwd())) == 0) {
                //登陆成功
                String time = request.getParameter("loginAuto");
                //设置自动登录时间
                int times = 100;
                if (time != null) {
                    if (time.equals("1h")) {
                        times = 60 * 60;
                    } else if (time.equals("1d")) {
                        times = 60 * 60 * 24;
                    } else if (time.equals("1m")) {
                        times = 60 * 60 * 24 * 30;
                    }
                    //账号和密码的cookie
                    Cookie nameCookie, pwdCookie;
                    nameCookie = new Cookie("userName", user.getUserName());
                    pwdCookie = new Cookie("userPwd", user.getUserPwd());
                    nameCookie.setMaxAge(times);
                    pwdCookie.setMaxAge(times);
                    response.addCookie(nameCookie);
                    response.addCookie(pwdCookie);
                }

                //加入到系统登陆名单中去
                application.setAttribute(user.getUserName(),user.getUserName());
                session.setAttribute("user",user);

                //如果不是从最起先不是从登陆页面进来
                String lastPath;
                if ((lastPath = (String) session.getAttribute("lastPath")) != null) {//判断登陆前的页面

                    //检验***
                    System.out.println("::"+lastPath);
                    response.sendRedirect(lastPath);  //登陆完成，重定向到之前访问的页面
                } else {
                    //检验***
                    System.out.println("::home");
                    response.sendRedirect("home");//登陆成功且第一次登陆，重定向到首页
                }

            } else if (loginCode == -1) {
                out.println("密码错误！请重新登陆");
                response.setHeader("refresh", "5;url=login");
            } else {
                out.println("用户名不存在！请重新登陆");
                response.setHeader("refresh", "5;url=login");
            }
        }
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
