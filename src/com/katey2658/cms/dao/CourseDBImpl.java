package com.katey2658.cms.dao;

import com.katey2658.cms.entity.Course;

import java.sql.*;
import java.util.Vector;

/**
 * Created by 11456 on 2016/11/21.
 */
public class CourseDBImpl {

    //
    public Course getCourseDetail(String courseId){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        Course course=null;
        try {
            conn=DBUtil.getConncetion();

            sql="select course_id,course_name,course_hours,course_teacher,course_credit,need_material,start_time,end_time from courses where course_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,courseId);

            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                course=new Course();
                course.setCourseId(rs.getString(1));
                course.setCourseName(rs.getString(2));
                course.setCourseHours(rs.getInt(3));
                course.setCourseTeacher(rs.getString(4));
                course.setCourseCredit(rs.getFloat(5));
                course.setNeedMaterial(rs.getBoolean(6));
                course.setStartTime(rs.getDate(7));
                course.setEndTime(rs.getDate(8));
            }

            DBUtil.closeAll(rs,ps,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  course;
    }


    //??????ε????
    public boolean deleteCourse(String courseId){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        boolean flag=true;
        try{
            conn=DBUtil.getConncetion();
            sql="delete from courses where course_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,courseId);
            if (ps.execute()){
                flag=true;
            }
            DBUtil.closeAll(null,ps,conn);
        } catch (SQLException e) {
            flag=false;
            e.printStackTrace();
        }
        return false;
    }

    //??????ε????
    public boolean updateCourse(Course course){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        boolean flag=false;
        try {
            conn = DBUtil.getConncetion();
            sql ="update courses set course_name=?,course_hours=?,course_teacher=?" +
                    ",course_credit=?,need_material=?,start_time=?,end_time=? where course_id=?";
            ps=conn.prepareStatement(sql);

            ps.setString(1,course.getCourseName());
            ps.setInt(2,course.getCourseHours());
            ps.setString(3,course.getCourseTeacher());
            ps.setFloat(4,course.getCourseCredit());
            ps.setBoolean(5,course.isNeedMaterial());
            ps.setDate(6,course.getStartTime());
            ps.setDate(7,course.getEndTime());
            ps.setString(8,course.getCourseId());

            System.out.println("-------------------"+course.getCourseTeacher());

            if(ps.executeUpdate()>0){
                flag=true;
            }
            DBUtil.closeAll(null,ps,conn);

        } catch (SQLException e) {
            flag=false;
            e.printStackTrace();
        }

        return flag;
    }



    //??????ε????
    public  boolean addCourse(Course course){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        boolean flag=false;
        try {
            conn=DBUtil.getConncetion();

            System.out.print("-------------------"+course.getCourseName());
            sql="insert into courses(course_id,course_name,course_hours,course_teacher" +
                    ",course_credit,need_material,start_time,end_time) values (?,?,?,?,?,?,?,?) ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,course.getCourseId());
            ps.setString(2,course.getCourseName());
            ps.setInt(3,course.getCourseHours());
            ps.setString(4,course.getCourseTeacher());
            ps.setFloat(5,course.getCourseCredit());
            ps.setBoolean(6,course.isNeedMaterial());
            ps.setDate(7,course.getStartTime());
            ps.setDate(8,course.getEndTime());

            if (ps.executeUpdate()!=0){
                flag=true;
            }

            DBUtil.closeAll(null,ps,conn);
        } catch (SQLException e) {
            flag=false;
            e.printStackTrace();
        }
        return  flag;
    }


    //??????
    public Vector<String> getHeaders() {
        Vector<String> headerVector = new Vector<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs=null;
        String s;
        String sql;
        try {
            conn = DBUtil.getConncetion();
            st=conn.createStatement();
            rs=st.executeQuery("SELECT * FROM courses");
            for (int i=1;i<=rs.getMetaData().getColumnCount();i++){
                s=rs.getMetaData().getColumnName(i);
                headerVector.add(s);
            }
            DBUtil.closeAll(rs,st,conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return headerVector;
    }


    //????????????
    public Vector<Course> getPage(int page, int pageNumbers){
        Vector<Course> courseVector=new Vector<Course>() ;
        Connection conn=null;
        CallableStatement cs=null;
        String sql;
        Course course=null;
        ResultSet rs=null;
        try{
            try {
                conn = DBUtil.getConncetion();

                sql = "CALL getpage(?,?)";
                cs= conn.prepareCall(sql);
                cs.setInt(1,page);
                cs.setInt(2,pageNumbers);

                rs = cs.executeQuery();

                while (rs.next()) {
                    course = new Course();

                    course.setCourseId(rs.getString(1));
                    course.setCourseName(rs.getString(2));
                    course.setCourseHours(rs.getInt(3));
                    course.setCourseTeacher(rs.getString(4));
                    course.setCourseCredit(rs.getFloat(5));
                    course.setNeedMaterial(rs.getBoolean(6));
                    course.setStartTime(rs.getDate(7));
                    course.setEndTime(rs.getDate(8));

                    courseVector.add(course);
                }
            }finally {
                DBUtil.closeAll(rs,cs,conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseVector;
    }
    //??????????
    public int getSumCount(){
        Connection conn=null;
        Statement ps=null;
        ResultSet rs=null;
        String sql;
        int countSum=0;

        try{
            try {
                conn = DBUtil.getConncetion();

                sql = "SELECT count(*) FROM  courses";
                ps = conn.createStatement();

                rs = ps.executeQuery(sql);
                if (rs.next()){
                    countSum=rs.getInt(1);
                }
            }finally {
                DBUtil.closeAll(rs,ps,conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countSum;

    }




    //??????е?????,???try catch??try finally?????
    public Vector<Course> getAll(){
        Vector<Course> courseVector=new Vector<Course>() ;
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        Course course=null;
        ResultSet rs=null;
        try{
            try {
                conn = DBUtil.getConncetion();

                sql = "SELECT course_id,course_name,course_hours,course_teacher,course_credit,need_material,start_time,end_time FROM courses";
                ps = conn.prepareStatement(sql);

                rs = ps.executeQuery();

                while (rs.next()) {
                    course = new Course();

                    course.setCourseId(rs.getString(1));
                    course.setCourseName(rs.getString(2));
                    course.setCourseHours(rs.getInt(3));
                    course.setCourseTeacher(rs.getString(4));
                    course.setCourseCredit(rs.getFloat(5));
                    course.setNeedMaterial(rs.getBoolean(6));
                    course.setStartTime(rs.getDate(7));
                    course.setEndTime(rs.getDate(8));

                    courseVector.add(course);
                }
            }finally {
                DBUtil.closeAll(rs,ps,conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseVector;
    }
}
