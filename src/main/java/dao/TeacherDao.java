package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dbutil.SQLHelper;
import entity.Course;
import entity.Files;
import entity.Homework;
import entity.Student;
import entity.Teacher;

public class TeacherDao {
	//教师登录验证密码是否正确(根据教师id返回密码)
    public String TeaPwdById(String id) {
    	String sql="select Tea_pwd from Teacher where Tea_id='"+id+"'";
    	String pwd = null;
    	ResultSet rs=SQLHelper.executeQuery(sql);
    	try {
    		while(rs.next()) {
    			pwd=rs.getString(1);
    			System.out.println(pwd);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	SQLHelper.closeConnection();
    	return pwd;
    }
    
    //判断教师用户是否存在
    public boolean is_teaExists(String id) {
    	boolean flag=false;
    	String sql="select * from teacher Where Tea_id="+id;
    	ResultSet rs = SQLHelper.executeQuery(sql);
    	String info;
        try{
            while (rs.next()) {
            	info=rs.getString(1);
                if(!info.equals(null)) {
                	flag=true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }
    
    //通过教师号码返回所有教师
    public Teacher queryTeacherById(String Id) {
        Teacher T = new Teacher();
        String sql = "select * from Teacher Where Tea_id="+Id;
        ResultSet rs = SQLHelper.executeQuery(sql);
        try{
            while (rs.next()) {
                T.setTea_id(rs.getString(1));
                T.setTea_name(rs.getString(2));
                T.setSex(rs.getString(3));
                T.setTea_pwd(rs.getString(4));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SQLHelper.closeConnection();
        return T;
    }
	
  //显示某人的所有文件信息
    public ArrayList<Files> allFiles(String id){
    	  ArrayList<Files> list = new ArrayList<Files>();
          String sql = "select * from resource_record where id='"+id+"'";
          ResultSet rs = SQLHelper.executeQuery(sql);
          try{
              while (rs.next()) {
            	  Files file = new Files();
                  file.setId(rs.getString(1));
                  file.setName(rs.getString(2));
                  file.setFileName(rs.getString(3));
                  list.add(file);
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          SQLHelper.closeConnection();
          return list;
    }
    
    
    //显示某教师的所教课程信息
    public ArrayList<Course> allTeachCourse(String Tea_id){
    	  ArrayList<Course> list = new ArrayList<Course>();
          String sql = "select * from course where Tea_id='"+Tea_id+"'";
          ResultSet rs = SQLHelper.executeQuery(sql);
          try{
              while (rs.next()) {
            	  Course course = new Course();
            	course.setCourse_id(rs.getString(1));
            	course.setTea_id(rs.getString(2));
            	course.setTea_name(rs.getString(3));
            	course.setCourse_name(rs.getString(4));
            	course.setClass(rs.getString(5));
                  list.add(course);
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          SQLHelper.closeConnection();
          System.out.println("老师教授课程list：");
          System.out.println(list.toString());
          return list;
    }
    
    
    //通过Course_id删除教师开设的课程(数据库中)
    
    public int deleteCourseByID(String id) {
    	 String sql = "delete from Course where Course_id='"+id+"';";
         SQLHelper. executeUpdate(sql);      
         return 1;
    }
    
    //教师开设新的课程
    public int CreateNewCourse(String Tea_id,String Tea_name,String Course_name,String Class) {
    	String sql="insert into course values(null,'"+Tea_id+"','"+Tea_name+"','"+Course_name+"','"+Class+"');";
    	SQLHelper.executeUpdate(sql);
    	return 1;
    }
    
    //返回某教师所发布的某门课程的信息
    public ArrayList<Homework> course_TeaHomework(String Course_name,String Tea_id){
  	  ArrayList<Homework> list = new ArrayList<Homework>();
        String sql = "select * from course_resource where Course_name='"+Course_name+"' and Tea_id='"+Tea_id+"';";
        ResultSet rs = SQLHelper.executeQuery(sql);
        try{
            while (rs.next()) {
          	  Homework homework = new Homework();
          	  homework.setResource_id(rs.getInt(1));
          	  System.out.println("老师查看自己上传的资源id："+rs.getInt(1));
          	  homework.setFile_Name(rs.getString(2));
          	  homework.setCourse_name(rs.getString(3));
          	  homework.setStu_Class(rs.getString(4));
          	  homework.setStart_time(rs.getString(5));
          	  homework.setOver_time(rs.getString(6));
          	  homework.setCourse_id(7);
          	  homework.setTea_id(rs.getString(8));
                list.add(homework);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SQLHelper.closeConnection();
        return list;
  }
    
    //删除某教师发布的某个作业
    public int deleteTeaHomework(int resource_id) {
   	 String sql = "delete from course_resource where resource_id='"+resource_id+"';";
        SQLHelper. executeUpdate(sql);      
        return 1;
   }
    
    //老师查看某课程某个作业的提交情况
    public ArrayList<Homework> course_Tea_StuHomework(int resource_id){
    	  ArrayList<Homework> list = new ArrayList<Homework>();
          String sql = "select * from submitted_hw where resource_id="+resource_id+";";
          ResultSet rs = SQLHelper.executeQuery(sql);
          try{
              while (rs.next()) {
            	  Homework homework = new Homework();
            	  homework.setStu_id(rs.getString(1));
            	  //System.out.println("老师查看自己上传的资源id："+rs.getInt(1));
            	  homework.setResource_id(rs.getInt(2));
            	  homework.setStu_name(rs.getString(3));
            	  homework.setFile_Name(rs.getString(4));
            	  homework.setSubmit_time(rs.getString(5));
                  list.add(homework);
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          SQLHelper.closeConnection();
          return list;
    }
    
    //老师打回作业（删除学生提交数据库的作业记录）
    public int Tea_returnHomework(int resource_id,String Stu_id ) {
      	 String sql = "delete from submitted_hw where resource_id="+resource_id+" and Stu_id='"+Stu_id+"';";
           SQLHelper. executeUpdate(sql);      
           return 1;
      }
    
    //老师发布新的作业
    public int Tea_newHomework(String filename,String Course_name,String Class,String Start_time,String Over_time,String Tea_id,int Course_id) {
    	String sql="insert into course_resource values(null,'"+filename+"','"+Course_name+"','"+Class+"','"+Start_time+"','"+Over_time+"',"+Course_id+",'"+Tea_id+"');";
    	SQLHelper.executeUpdate(sql);
    	System.out.println("数据库里的文件名为："+filename);
    	return 1;
    	
    	
    	
    }

    public Teacher queryTeacherById1(String Id) {
        Teacher T = new Teacher();
        String sql = "select * from Teacher Where Tea_id="+Id;
        ResultSet rs = SQLHelper.executeQuery(sql);
        try{
            while (rs.next()) {
                T.setTea_id(rs.getString(1));
                T.setTea_name(rs.getString(2));
                T.setSex(rs.getString(3));
                T.setTea_pwd(rs.getString(4));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SQLHelper.closeConnection();
        return T;
    }
    
    //显示所有老师
    public ArrayList<Teacher> allTeachers() {
        ArrayList<Teacher> list = new ArrayList<Teacher>();
        String sql = "select * from teacher";
        ResultSet rs = SQLHelper.executeQuery(sql);
        try{
            while (rs.next()) {
                Teacher tea = new Teacher();
                tea.setTea_id(rs.getString(1));
                tea.setTea_name(rs.getString(2));
                tea.setSex(rs.getString(3));
                tea.setTea_pwd(rs.getString(4));       
                list.add(tea);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SQLHelper.closeConnection();
        return list;
    }
	
    
	
	
}
