package dao;
import dbutil.SQLHelper;
import entity.Course;
//import entity.Customer;
import entity.Files;
import entity.Homework;
import entity.Student;
import java.sql.*;
import java.util.*;

public class StudentDao {
    public ArrayList<Student> allStudents() {
        ArrayList<Student> list = new ArrayList<Student>();
        String sql = "select * from student";
        ResultSet rs = SQLHelper.executeQuery(sql);
        try{
            while (rs.next()) {
                Student stu = new Student();
                stu.setStu_id(rs.getString(1));
                stu.setStu_name(rs.getString(2));
                stu.setStu_sex(rs.getString(3));
                stu.setStu_pwd(rs.getString(4));
                stu.setStu_Class(rs.getString(5));
                list.add(stu);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SQLHelper.closeConnection();
        return list;
    }
    
    //�ж��Ƿ���ڸ�ѧ���û�
    public boolean is_stuExists(String id) {
    	boolean flag=false;
    	String sql="select * from student Where Stu_id="+id;
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
    
    public Student queryStudentById(String Id) {
        Student S = new Student();
        String sql = "select * from student Where Stu_id="+Id;
        ResultSet rs = SQLHelper.executeQuery(sql);
        try{
            while (rs.next()) {
                S.setStu_id(rs.getString(1));
                S.setStu_name(rs.getString(2));
                S.setStu_sex(rs.getString(3));
                S.setStu_pwd(rs.getString(4));
                S.setStu_Class(rs.getString(5));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SQLHelper.closeConnection();
        return S;
    }
    
    
    public void updateStudent(Student s) {
    	String id=s.getStu_id();
    	if(queryStudentById(id)!=null) {
    		String sql = "update student set Stu_name='"+s.getStu_name()+"',Stu_pwd='"+s.getStu_pwd()+"' where cusid="+s.getStu_id();
            SQLHelper.executeUpdate(sql);
    	}
    }
    
    //����ѧ����Ϣ����
    public void updateStudent(String id,String name,String phone) {
        //Student S = new Student();
        String sql = "update Student set Stu_id='"+id+"',cunphone='"+phone+"' where cusid="+id;
        SQLHelper.executeUpdate(sql);
    }
    
    //ѧ���Լ��޸�����
    public void updateStudentPwd(String id,String newPwd) {
    	String sql="update Student set Stu_pwd='"+newPwd+"' where Stu_id='"+id+"'";
    	SQLHelper.executeUpdate(sql);
    }
    
    
    public int deleteStudentByID(String id) {
        String sql = "delete from student where Stu_id="+id;
        SQLHelper. executeUpdate(sql);
        return 1;
    }
    
    //���ѧ������
    public int addStudent(String id,String name,String phone) {
        String sql = "INSERT INTO student VALUES ('"+id+"','"+name+"','"+phone+"');";
        SQLHelper. executeUpdate(sql);
        return 1;
    }
    
    //ѧ����¼��֤�����Ƿ���ȷ(����ѧ��id��������)
    public String StuPwdById(String id) {
    	String sql="select Stu_pwd from Student where Stu_id='"+id+"'";
    	String pwd = null;
    	ResultSet rs=SQLHelper.executeQuery(sql);
    	try {
    		while(rs.next()) {
    			pwd=rs.getString(1);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	SQLHelper.closeConnection();
    	return pwd;
    }
    
    //ѧ���ϴ��ļ���ͬʱ���ļ���Ϣ�������ݿ�
    public int StuUploadFileInfo(String id,String Name,String FileName) {
    	String sql="insert into Resource_Record values('"+id+"','"+Name+"','"+FileName+"');";
    	SQLHelper.executeUpdate(sql);
    	return 1;
    }
    
    //��ȡѧ���ϴ��ļ�����Ϣ
    public String[][] StuGetFileInfo(String id){
    	//�������û����ϴ������ļ�����
    	int countFileNum=0;
    	//���û��ϴ��ı�ʱ��ļ�����
    	//int count=0;
    	String sqlCount="select count(id) from resource_record where id='"+id+"';";
    	
    	ResultSet rs=SQLHelper.executeQuery(sqlCount);
    	try {
    		while(rs.next()) {
    			countFileNum=rs.getInt(1);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	String[][] F_info=new String[countFileNum][2];
    	String sql="select Path,FileName from resource_record where id='"+id+"';";
    	ResultSet rs1=SQLHelper.executeQuery(sql);
    	try {
    		int count=0;
    		while(rs1.next()) {
    			F_info[count][0]=rs1.getString(1);
    			F_info[count][1]=rs1.getString(2);
    			System.out.println(F_info[count][0]+" "+F_info[count][1]);
    			count++;
    			
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return F_info;	
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
    //ͨ��idɾ��ѧ���ļ��������ݿ��У�
    public int deleteFilesByID(String id,String FileName) {
    	 String sql = "delete from Resource_Record where id='"+id+"' and FileName='"+FileName+"'";
         SQLHelper. executeUpdate(sql);
         return 1;
    }
    
    //��ʾѧ�����ĸ��༶��
    public String showClassofStudent(String id) {
    	String sql="select Class from student where Stu_id='"+id+"'";
    	String Class="";
    	 ResultSet rs = SQLHelper.executeQuery(sql);
    	 try{
             while (rs.next()) {
           	  Class=rs.getString(1);
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         }
    	 return Class;
    	
    }
    
    //��ʾĳ�����пγ���Ϣ
    public ArrayList<Course> allCourse(String Class){
  	  ArrayList<Course> list = new ArrayList<Course>();
        String sql = "select * from course where Class='"+Class+"'";
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
        return list;
  }
    
    //��ʾĳ�γ̵���ҵ
    public ArrayList<Homework> course_Homework(String Class){
    	  ArrayList<Homework> list = new ArrayList<Homework>();
          String sql = "select * from course_resource where Class='"+Class+"'";
          ResultSet rs = SQLHelper.executeQuery(sql);
          try{
              while (rs.next()) {
            	  Homework homework = new Homework();
            	  homework.setResource_id(rs.getInt(1));
            	  System.out.println(rs.getInt(1));
            	  homework.setFile_Name(rs.getString(2));
            	  homework.setCourse_name(rs.getString(3));
            	  homework.setStu_Class(rs.getString(4));
            	  homework.setStart_time(rs.getString(5));
            	  homework.setOver_time(rs.getString(6));
                  list.add(homework);
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          SQLHelper.closeConnection();
          return list;
    }
    
    //ѧ���ύ��ҵ�󣬽��ύ��Ϣд���ύ��ҵ��Ϣ����
    public int StuUploadHwInfo(String Stu_id,int Resource_id,String Stu_name,String FileName,String datetime,String Is_submitted) {
    	String sql="insert into Submitted_hw values('"+Stu_id+"',"+Resource_id+",'"+Stu_name+"','"+FileName+"','"+datetime+"','"+Is_submitted+"');";
    	SQLHelper.executeUpdate(sql);
    	return 1;
    }
    
    //��ʾĳ�γ���ҵʱ���ж��Ƿ��ύ����ʾ�ڽ���
    public String judgeIs_submitted(String Stu_id,int resource_id) {
    	String is_submitted="";
    	String sql="select Is_submitted from submitted_hw where Stu_id='"+Stu_id+"' and resource_id="+resource_id+";";
    	ResultSet rs = SQLHelper.executeQuery(sql);
   	 try{
            while (rs.next()) {
            	is_submitted=rs.getString(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   	 System.out.println("�ж��Ƿ��ύ��"+is_submitted);
   	 return is_submitted;
   	 
    }
    
    //��ʾ�γ���ҵʱ��ʾ�ύʱ��
    public String showSubmittedTime(String id,int resource_id) {
    	String subtime="";
    	String sql="select Submit_time from submitted_hw where Stu_id='"+id+"' and resource_id="+resource_id+";";
    	ResultSet rs = SQLHelper.executeQuery(sql);
      	 try{
               while (rs.next()) {
            	   subtime=rs.getString(1);
               }
           } catch (Exception ex) {
               ex.printStackTrace();
           }
      	 System.out.println("�鿴ѧ���ύʱ�䣺"+subtime);
      	 return subtime;
    }
    
    //ɾ��ѧ���ύ����ҵ(���ݿ���)
    public int stu_deleteHw(String id,String FileName,int resource_id) {
    	 String sql = "delete from submitted_hw where Stu_id='"+id+"' and File_Name='"+FileName+"' and resource_id="+resource_id+";";
         SQLHelper. executeUpdate(sql);
         return 1;
    }
    
    
    
}