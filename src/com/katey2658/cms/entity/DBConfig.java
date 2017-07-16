package com.katey2658.cms.entity;

import javax.servlet.ServletConfig;

/**
 * Created by 11456 on 2016/11/21.
 */
public class DBConfig {

    /**
     * 无参构造方法
     */
    public DBConfig(){
        //默认连接方式，应该设置修改
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/loginuser";
        dbUserName="root";
        dbUserPwd="wd15268481127";
    }

    /**
     * 有残构造方法
     * @param config 配置文件
     */
    public DBConfig(ServletConfig config){

        setDriver(config.getInitParameter("DRIVER"));
        setUrl(config.getInitParameter("URL"));
        setDbUserName(config.getInitParameter("USER_NAME"));
        setDbUserPwd(config.getInitParameter("USER_PASSWD"));

    }

    /**
     * 驱动
     */
    private  String driver;

    public  void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }

    /**
     * 数据库，以及连接方式
     */
    private  String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 数据库用户名字
     */
    private static String dbUserName;

    public static void setDbUserName(String dbUserName) {
        DBConfig.dbUserName = dbUserName;
    }

    public static String getDbUserName() {
        return dbUserName;
    }
    /**
     * 数据库用户密码
     */
    private static String dbUserPwd;

    public static void setDbUserPwd(String dbUserPwd) {
        DBConfig.dbUserPwd = dbUserPwd;
    }

    public static String getDbUserPwd() {
        return dbUserPwd;
    }
}
