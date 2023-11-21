package entity;

public class Teacher {
	private String Tea_id;
    private String Tea_name,Tea_pwd,Sex;
  
    public Teacher() {
    	
    }
    public Teacher(String id,String name,String pwd) {
    	this.Tea_id=id;this.Tea_name=name;this.Tea_pwd=pwd;
    }
	public String getTea_id() {
		return Tea_id;
	}
	public void setTea_id(String id) {
		this.Tea_id = id;
	}
	public String getTea_name() {
		return Tea_name;
	}
	public void setTea_name(String name) {
		this.Tea_name = name;
	}
	public String getTea_pwd() {
		return Tea_pwd;
	}
	public void setTea_pwd(String pwd) {
		this.Tea_pwd = pwd;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
}
