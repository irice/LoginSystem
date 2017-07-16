package com.katey2658.cms.service;

import com.katey2658.cms.dao.CourseDBImpl;
import com.katey2658.cms.dao.UserDBImpl;
import com.katey2658.cms.entity.Course;
import com.katey2658.cms.entity.User;

import java.util.Vector;

/**
 * Created by 11456 on 2016/11/21.
 */
public class UserService {

    UserDBImpl userDB=null;
    CourseDBImpl courseDB=null;
    public UserService(){
        userDB=new UserDBImpl();
        courseDB=new CourseDBImpl();
    }
    //��½��ѯ
    public int login(User user){
        return userDB.login(user.getUserName(),user.getUserPwd());
    }
    //ɾ��
    public boolean deleteUser(String userName){
        return userDB.deleteUser(userName);
    }
    //�޸�
    public boolean updateUser(User newUser){
        return userDB.updateUser(newUser);
    }
    //����
    public boolean addUser(User newUser) {
        return userDB.addUser(newUser);
    }
    //��ȡ���пγ�
    public Vector<Course> getAllCourses(){
        return courseDB.getAll();
    }
    //ɾ��һ�ſγ�
    public  boolean deleteCourse(String courseId){
        return courseDB.deleteCourse(courseId);
    }
    //��ѯһ�ſ�
    public Course courseDetail(String courseId){
        return courseDB.getCourseDetail(courseId);
    }
    //����һ�ſ�
    public  boolean updateCourse(Course course){
        return courseDB.updateCourse(course);
    }
    //����һ�ſ�
    public  boolean addCourse(Course course) {
        return courseDB.addCourse(course);
    }
    //��ȡ��ͷ
    public  Vector<String> getHeaders(){
        return  courseDB.getHeaders();
    }
    //��ȡĳһҳ�γ̵�����
    public Vector<Course> getpage(int page){
        return courseDB.getPage(page,10);
    }
    //��ȡ��ҳ��
    public int getPageSumCount(){
        return courseDB.getSumCount()/10+1;
    }
}
