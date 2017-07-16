package com.katey2658.cms.dao;

import com.katey2658.cms.entity.User;

import java.sql.*;

/**
 * Created by 11456 on 2016/11/21.
 */
public class UserDBImpl {

    //登陆用户验证，如果有该用户就返回相关该用户核心信息
    public  int login(String userName,String userPwd){
        Connection connection=null;
        PreparedStatement statement=null;
        String sql;
        String passwd=null;
        int returnInt=-2;
        try {
            connection=DBUtil.getConncetion();

            sql="select username,passpwd from user where username=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,userName);

            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()) {
                passwd = resultSet.getString(2);
                if (passwd.equals(userPwd)) {
                    returnInt = 0;
                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  returnInt;
    }

    //删除一个用户
    public boolean deleteUser(String userName){
        Connection conn=null;
        Statement st=null;
        String sql;
        boolean flag=false;
        try{
            conn=DBUtil.getConncetion();

            sql="delete from user where username=? and  passpwd=?";
            st=conn.createStatement();
            if (st.executeUpdate(sql)>0){
                flag=true;
            }
        } catch (SQLException e) {
            flag=false;
            e.printStackTrace();
        }
        return flag;
    }

    //修改一个用户的信息
    public boolean updateUser(User user){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        boolean flag=false;
        try{
            conn=DBUtil.getConncetion();

            sql="update user set passpwd=? where username=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUserPwd());
            ps.setString(2,user.getUserName());

            if(ps.execute()){
                flag=true;
            }
        } catch (SQLException e) {
            flag=false;
            e.printStackTrace();
        }
        return false;
    }


    //添加一个用户信息记录到数据库
    public boolean addUser(User user){
        boolean addFlag=false;
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;

        try {
            conn=DBUtil.getConncetion();
            sql="insert into user(username,passpwd) values (?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getUserPwd());

            if (ps.executeUpdate()>0){
                addFlag=true;
            }


        } catch (SQLException e) {
            addFlag=false;
            e.printStackTrace();
        }
        return addFlag;
    }
}
