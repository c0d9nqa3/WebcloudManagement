package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dbutil.SQLHelper;
import entity.Files;

public class AdminDao {
	//判断是否存在该管理员用户
    public boolean is_AdminExists(String id) {
    	boolean flag=false;
    	String sql="select * from Admin Where Admin_id='"+id+"'";
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
    
    //获取正确的用户密码
    public String adminPwdById(String id) {   	
        	String sql="select Admin_pwd from Admin where Admin_id='"+id+"'";
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
    
    //管理员自己修改密码
    public void updateAdminPwd(String id,String newPwd) {
    	String sql="update Admin set Admin_pwd='"+newPwd+"' where Admin_id='"+id+"'";
    	SQLHelper.executeUpdate(sql);
    }
    
    //显示规章制度文件
    public ArrayList<Files> showRuleFiles(){
    	  ArrayList<Files> list = new ArrayList<Files>();
          String sql = "select * from Rules_File ";
          ResultSet rs = SQLHelper.executeQuery(sql);
          try{
              while (rs.next()) {
            	  Files file = new Files();
                  file.setResource_id(rs.getInt(1));              
                  file.setFileName(rs.getString(2));
                  list.add(file);
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          SQLHelper.closeConnection();
          return list;
    }
    
    //删除规章制度(数据库中)
    public int Admin_deleteRule(int resource_id) {
    	 String sql = "delete from Rules_File where RulesResourceId="+resource_id+";";
         SQLHelper. executeUpdate(sql);
         return 1;
    }
    
  //显示申请表
    public ArrayList<Files> showTableFiles(){
    	  ArrayList<Files> list = new ArrayList<Files>();
          String sql = "select * from Submit_table_file ";
          ResultSet rs = SQLHelper.executeQuery(sql);
          try{
              while (rs.next()) {
            	  Files file = new Files();
                               
                  file.setFileName(rs.getString(1));
                  file.setResource_id(rs.getInt(2)); 
                  list.add(file);
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          SQLHelper.closeConnection();
          return list;
    }
    
  //删除申请表(数据库中)
    public int Admin_deleteTable(int resource_id) {
    	 String sql = "delete from submit_table_file where TableResourceId="+resource_id+";";
         SQLHelper. executeUpdate(sql);
         return 1;
    }
    
    
    //显示学生工作文件
      public ArrayList<Files> showStudentWorkFiles(){
      	  ArrayList<Files> list = new ArrayList<Files>();
            String sql = "select * from Studentwork_File ";
            ResultSet rs = SQLHelper.executeQuery(sql);
            try{
                while (rs.next()) {
              	  Files file = new Files();
                                  
                    file.setFileName(rs.getString(1));
                    file.setResource_id(rs.getInt(2));
                    list.add(file);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            SQLHelper.closeConnection();
            return list;
      }
      
      //显示视频文件
      public ArrayList<Files> showVideo(){
      	  ArrayList<Files> list = new ArrayList<Files>();
            String sql = "select * from Video_File ";
            ResultSet rs = SQLHelper.executeQuery(sql);
            try{
                while (rs.next()) {
              	  Files file = new Files();
                    file.setResource_id(rs.getInt(1));              
                    file.setFileName(rs.getString(2));
                    
                    list.add(file);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            SQLHelper.closeConnection();
            return list;
      }
      
    //删除学生工作文件(数据库中)
      public int Admin_deleteStudentWorkFile(int resource_id) {
      	 String sql = "delete from Studentwork_File where StuResourceId="+resource_id+";";
           SQLHelper. executeUpdate(sql);
           return 1;
      }
      
      //删除视频文件
      public int Admin_deleteVideo(int resource_id) {
       	 String sql = "delete from video_file where video_id="+resource_id+";";
            SQLHelper. executeUpdate(sql);
            return 1;
       }
      
      //插入学生工作文档
      public int create_Student_workfile(String filename) {
    	  String sql="insert into studentwork_file values('"+filename+"',null);";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      
      //插入申请表
      public int create_table(String filename) {
    	  String sql="insert into submit_table_file values('"+filename+"',null);";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
    //插入申请表
      public int create_rule(String filename) {
    	  String sql="insert into rules_file values(null,'"+filename+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //插入视频信息
      public int create_video(String filename) {
    	  String sql="insert into Video_file values(null,'"+filename+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
    
      //管理员修改学生信息
      public int editStu_info(String Stu_id,String name,String sex,String pwd,String Class) {
    	  String sql="update student set Stu_id='"+Stu_id+"',Stu_name='"+name+"',Sex='"+sex+"',Stu_pwd='"+pwd+"',Class='"+Class+"' where Stu_id='"+Stu_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //管理员删除某学生
      public int delStu(String Stu_id) {
    	  String sql = "delete from student where Stu_id='"+Stu_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //管理员添加某学生
      public int addStu(String Stu_id,String Stu_name,String sex,String pwd,String Class) {
    	  String sql="insert into student values('"+Stu_id+"','"+Stu_name+"','"+sex+"','"+pwd+"','"+Class+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
    	  
      }
    
      //管理员修改老师信息
      public int editTea_info(String Tea_id,String name,String sex,String pwd) {
    	  String sql="update teacher set Tea_id='"+Tea_id+"',Tea_name='"+name+"',Sex='"+sex+"',Tea_pwd='"+pwd+"' where Tea_id='"+Tea_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //管理员删除某老师
      public int delTea(String Tea_id) {
    	  String sql = "delete from teacher where Tea_id='"+Tea_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //管理员添加某老师
      public int addTea(String Tea_id,String Tea_name,String sex,String pwd) {
    	  String sql="insert into teacher values('"+Tea_id+"','"+Tea_name+"','"+sex+"','"+pwd+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
    	  
      }
      
      
      
}


