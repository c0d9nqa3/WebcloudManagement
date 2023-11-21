package entity;

public class Course {
	private String Course_id,Tea_name,Course_name,Class,Tea_id;
	
	public Course(String Course_id,String Tea_name,String Course_name,String Class) {
		this.setCourse_id(Course_id);this.setTea_name(Tea_name);this.setCourse_name(Course_name);this.setClass(Class);
	}
	
	public Course() {
		
	}

	public String getCourse_id() {
		return Course_id;
	}

	public void setCourse_id(String course_id) {
		Course_id = course_id;
	}

	public String getTea_name() {
		return Tea_name;
	}

	public void setTea_name(String tea_name) {
		Tea_name = tea_name;
	}

	public String getCourse_name() {
		return Course_name;
	}

	public void setCourse_name(String course_name) {
		Course_name = course_name;
	}

	public String get_Class() {
		return Class;
	}

	public void setClass(String class1) {
		Class = class1;
	}

	public String getTea_id() {
		return Tea_id;
	}

	public void setTea_id(String tea_id) {
		Tea_id = tea_id;
	}
}
