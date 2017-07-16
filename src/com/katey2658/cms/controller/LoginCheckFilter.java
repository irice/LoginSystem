package com.katey2658.cms.controller;

import com.katey2658.cms.common.DBUtilServlet;
import com.katey2658.cms.entity.User;
import com.katey2658.cms.service.UserFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by 11456 on 2016/12/2.
 */
public class LoginCheckFilter extends DBUtilServlet implements Filter {

    Vector<String> vector=null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        vector=new Vector<>();
        String urlName;
        for (int itme='A';itme<='Z';itme++){
            urlName=filterConfig.getInitParameter("excludeUrlB");
            if (urlName!=null){
                vector.add(urlName);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        HttpSession session=request.getSession();

        String uri=request.getRequestURI();
        if (uri!=null){
            String uriF=uri.substring(uri.lastIndexOf("/"));
            if (vector.contains(uriF)){
                return;
            }
        }


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
            //验证账号有效性;;
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
    }


    @Override
    public void destroy() {

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
