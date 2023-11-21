package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dbutil.SQLHelper;
import entity.Files;

public class AdminDao {
	//�ж��Ƿ���ڸù���Ա�û�
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
    
    //��ȡ��ȷ���û�����
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
    
    //����Ա�Լ��޸�����
    public void updateAdminPwd(String id,String newPwd) {
    	String sql="update Admin set Admin_pwd='"+newPwd+"' where Admin_id='"+id+"'";
    	SQLHelper.executeUpdate(sql);
    }
    
    //��ʾ�����ƶ��ļ�
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
    
    //ɾ�������ƶ�(���ݿ���)
    public int Admin_deleteRule(int resource_id) {
    	 String sql = "delete from Rules_File where RulesResourceId="+resource_id+";";
         SQLHelper. executeUpdate(sql);
         return 1;
    }
    
  //��ʾ�����
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
    
  //ɾ�������(���ݿ���)
    public int Admin_deleteTable(int resource_id) {
    	 String sql = "delete from submit_table_file where TableResourceId="+resource_id+";";
         SQLHelper. executeUpdate(sql);
         return 1;
    }
    
    
    //��ʾѧ�������ļ�
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
      
      //��ʾ��Ƶ�ļ�
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
      
    //ɾ��ѧ�������ļ�(���ݿ���)
      public int Admin_deleteStudentWorkFile(int resource_id) {
      	 String sql = "delete from Studentwork_File where StuResourceId="+resource_id+";";
           SQLHelper. executeUpdate(sql);
           return 1;
      }
      
      //ɾ����Ƶ�ļ�
      public int Admin_deleteVideo(int resource_id) {
       	 String sql = "delete from video_file where video_id="+resource_id+";";
            SQLHelper. executeUpdate(sql);
            return 1;
       }
      
      //����ѧ�������ĵ�
      public int create_Student_workfile(String filename) {
    	  String sql="insert into studentwork_file values('"+filename+"',null);";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      
      //���������
      public int create_table(String filename) {
    	  String sql="insert into submit_table_file values('"+filename+"',null);";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
    //���������
      public int create_rule(String filename) {
    	  String sql="insert into rules_file values(null,'"+filename+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //������Ƶ��Ϣ
      public int create_video(String filename) {
    	  String sql="insert into Video_file values(null,'"+filename+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
    
      //����Ա�޸�ѧ����Ϣ
      public int editStu_info(String Stu_id,String name,String sex,String pwd,String Class) {
    	  String sql="update student set Stu_id='"+Stu_id+"',Stu_name='"+name+"',Sex='"+sex+"',Stu_pwd='"+pwd+"',Class='"+Class+"' where Stu_id='"+Stu_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //����Աɾ��ĳѧ��
      public int delStu(String Stu_id) {
    	  String sql = "delete from student where Stu_id='"+Stu_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //����Ա���ĳѧ��
      public int addStu(String Stu_id,String Stu_name,String sex,String pwd,String Class) {
    	  String sql="insert into student values('"+Stu_id+"','"+Stu_name+"','"+sex+"','"+pwd+"','"+Class+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
    	  
      }
    
      //����Ա�޸���ʦ��Ϣ
      public int editTea_info(String Tea_id,String name,String sex,String pwd) {
    	  String sql="update teacher set Tea_id='"+Tea_id+"',Tea_name='"+name+"',Sex='"+sex+"',Tea_pwd='"+pwd+"' where Tea_id='"+Tea_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //����Աɾ��ĳ��ʦ
      public int delTea(String Tea_id) {
    	  String sql = "delete from teacher where Tea_id='"+Tea_id+"';";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
      }
      
      //����Ա���ĳ��ʦ
      public int addTea(String Tea_id,String Tea_name,String sex,String pwd) {
    	  String sql="insert into teacher values('"+Tea_id+"','"+Tea_name+"','"+sex+"','"+pwd+"');";
    	  SQLHelper.executeUpdate(sql);
    	  return 1;
    	  
      }
      
      
      
}


