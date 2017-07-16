package com.katey2658.cms.service;

/**
 * Created by 11456 on 2016/11/21.
 */
public class UserFactory {

    private UserService userService=null;
    public UserService getUserService(){
        if (userService==null){
            userService=new UserService();
        }
        return  userService;
    }
    public  void setUserService(UserService userService){
        this.userService=userService;
    }
}
