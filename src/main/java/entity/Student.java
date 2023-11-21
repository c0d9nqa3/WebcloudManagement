package entity;

public class Student {
	private String Stu_id;
    private String Stu_name,Stu_pwd,Stu_sex,Class;
  
    public Student() {
    	
    }
    
    public Student(String id,String name,String pwd,String sex,String Class) {
    	this.Stu_id=id;this.Stu_name=name;this.Stu_pwd=pwd;this.setStu_sex(sex);
    	this.Class=Class;
    }
    
    public Student(String id,String pwd) {
    	this.Stu_id=id;this.Stu_pwd=pwd;
    }
	public String getStu_id() {
		return Stu_id;
	}
	public void setStu_id(String id) {
		this.Stu_id = id;
	}
	public String getStu_name() {
		return Stu_name;
	}
	public void setStu_name(String name) {
		this.Stu_name = name;
	}
	public String getStu_pwd() {
		return Stu_pwd;
	}
	public void setStu_pwd(String pwd) {
		this.Stu_pwd = pwd;
	}

	public String getStu_sex() {
		return Stu_sex;
	}

	public void setStu_sex(String stu_sex) {
		Stu_sex = stu_sex;
	}
	public void setStu_Class(String Class) {
		this.Class=Class;
		
	}
	public String getStu_Class() {
		return Class;
	}
}
