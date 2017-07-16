package com.katey2658.cms.common;

import com.katey2658.cms.entity.DBConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.*;

/**
 * Created by 11456 on 2016/11/21.
 */
public class DBUtilServlet extends HttpServlet{

    protected ServletConfig config=null;

    protected Connection conn=null;//连接
    protected Statement st=null;//声明
    protected ResultSet rs=null;//返回结果集
    protected String sql="";

    protected boolean DBOpen=false;//是否已经连接

    public boolean isDBOpen() {
        return DBOpen;
    }

    /**
     * 获取数据库连接
     * @return
     */
    protected  boolean getConnect(){
        DBConfig dbConfig=new DBConfig();


        //检测是否取到数据
        System.out.print("--------------------"+dbConfig.getUrl());

        //如果没配置好数据库该怎么？

        //做一个关于数据库没有写死的,或者配置文件不对
        try {
            Class.forName(dbConfig.getDriver());
            conn= DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getDbUserName(),dbConfig.getDbUserPwd());
            st=conn.createStatement();
            //设置数据库已经连接
            DBOpen=true;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            //应该做一些关于数据库没连上的事情
            System.out.println(":数据库已经连接");
        }
        return false;
    }


    /**
     *  重新连接，需要先关闭可能的连接
     */
    public boolean reConnect(){
        if (conn!=null||st!=null||rs!=null){
            closeAll(rs,st,conn);
        }
        return getConnect();
    }

    /**
     * 首先执行，获取config对象
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config=config;
        super.init(config);
    }


    /**
     * 第二个执行，连接数据库
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        getConnect();
    }

    /**
     * 销毁servlet,先关闭各种连接，然后销毁
     */
    @Override
    public void destroy() {
        closeAll(rs,st,conn);
        super.destroy();
    }

    /**
     * 数据是否连接
     * @return
     */
    public boolean getIsConnected(){
        return DBOpen;
    }

    /**
     * 关闭对于的数据库的连接，但是需先要做判断是否为空
     * @param resultSet
     * @param statement
     * @param connection
     */
    protected void closeAll(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (connection!=null){
                connection.close();
            }
            //连接为假
            DBOpen=false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
