package com.katey2658.cms.entity;

/**
 * Created by 11456 on 2016/11/21.
 */
public class User {

    public User(){
    }

    public User(String userName,String userPwd){
        this(userName,userPwd,null);
    }

    public User(String userName,String userPwd,String userMail){
        this.userName=userName;
        this.userPwd=userPwd;
        this.userMail=userMail;
    }


    /**
     * 用户名
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户密码
     */
    private String userPwd;

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 用户邮箱
     */
    private String userMail;

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserMail() {
        return userMail;
    }

}
