package com.katey2658.cms.dao;

import java.sql.*;

/**
 * Created by 11456 on 2016/11/21.
 */
public class DBUtil {

    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/loginuser?useUnicode=true&characterEncoding=utf-8";
    private static final String USER_NAME="root";
    private static final String USER_PASSWD="wd15268481127";

    //1.注册驱动
    static{
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //2.获取数据库连接
    public static Connection getConncetion(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(URL,USER_NAME,USER_PASSWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭对于的数据库的连接
    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection){
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
