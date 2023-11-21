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
	//��ʦ��¼��֤�����Ƿ���ȷ(���ݽ�ʦid��������)
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
    
    //�жϽ�ʦ�û��Ƿ����
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
    
    //ͨ����ʦ���뷵�����н�ʦ
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
	
  //��ʾĳ�˵������ļ���Ϣ
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
    
    
    //��ʾĳ��ʦ�����̿γ���Ϣ
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
          System.out.println("��ʦ���ڿγ�list��");
          System.out.println(list.toString());
          return list;
    }
    
    
    //ͨ��Course_idɾ����ʦ����Ŀγ�(���ݿ���)
    
    public int deleteCourseByID(String id) {
    	 String sql = "delete from Course where Course_id='"+id+"';";
         SQLHelper. executeUpdate(sql);      
         return 1;
    }
    
    //��ʦ�����µĿγ�
    public int CreateNewCourse(String Tea_id,String Tea_name,String Course_name,String Class) {
    	String sql="insert into course values(null,'"+Tea_id+"','"+Tea_name+"','"+Course_name+"','"+Class+"');";
    	SQLHelper.executeUpdate(sql);
    	return 1;
    }
    
    //����ĳ��ʦ��������ĳ�ſγ̵���Ϣ
    public ArrayList<Homework> course_TeaHomework(String Course_name,String Tea_id){
  	  ArrayList<Homework> list = new ArrayList<Homework>();
        String sql = "select * from course_resource where Course_name='"+Course_name+"' and Tea_id='"+Tea_id+"';";
        ResultSet rs = SQLHelper.executeQuery(sql);
        try{
            while (rs.next()) {
          	  Homework homework = new Homework();
          	  homework.setResource_id(rs.getInt(1));
          	  System.out.println("��ʦ�鿴�Լ��ϴ�����Դid��"+rs.getInt(1));
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
    
    //ɾ��ĳ��ʦ������ĳ����ҵ
    public int deleteTeaHomework(int resource_id) {
   	 String sql = "delete from course_resource where resource_id='"+resource_id+"';";
        SQLHelper. executeUpdate(sql);      
        return 1;
   }
    
    //��ʦ�鿴ĳ�γ�ĳ����ҵ���ύ���
    public ArrayList<Homework> course_Tea_StuHomework(int resource_id){
    	  ArrayList<Homework> list = new ArrayList<Homework>();
          String sql = "select * from submitted_hw where resource_id="+resource_id+";";
          ResultSet rs = SQLHelper.executeQuery(sql);
          try{
              while (rs.next()) {
            	  Homework homework = new Homework();
            	  homework.setStu_id(rs.getString(1));
            	  //System.out.println("��ʦ�鿴�Լ��ϴ�����Դid��"+rs.getInt(1));
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
    
    //��ʦ�����ҵ��ɾ��ѧ���ύ���ݿ����ҵ��¼��
    public int Tea_returnHomework(int resource_id,String Stu_id ) {
      	 String sql = "delete from submitted_hw where resource_id="+resource_id+" and Stu_id='"+Stu_id+"';";
           SQLHelper. executeUpdate(sql);      
           return 1;
      }
    
    //��ʦ�����µ���ҵ
    public int Tea_newHomework(String filename,String Course_name,String Class,String Start_time,String Over_time,String Tea_id,int Course_id) {
    	String sql="insert into course_resource values(null,'"+filename+"','"+Course_name+"','"+Class+"','"+Start_time+"','"+Over_time+"',"+Course_id+",'"+Tea_id+"');";
    	SQLHelper.executeUpdate(sql);
    	System.out.println("���ݿ�����ļ���Ϊ��"+filename);
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
    
    //��ʾ������ʦ
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
