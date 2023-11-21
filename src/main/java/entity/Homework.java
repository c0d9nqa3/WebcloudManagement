package entity;

public class Homework {
	private String File_Name,Course_name,Class,Start_time,Over_time,Tea_id,Stu_id,Stu_name,Submit_time;
	private int resource_id,Course_id;
	
    public Homework() {
    	
    }
    
    public Homework(int resource_id,String File_Name,String Course_name,String Class,String is_submit) {
    	this.setResource_id(resource_id);this.setFile_Name(File_Name);this.setCourse_name(Course_name);this.Class=Class;
    	this.Class=Class;
    }
    
    
	public void setStu_Class(String Class) {
		this.Class=Class;
		
	}
	public String getStu_Class() {
		return Class;
	}
	public int getCourse_id() {
		return Course_id;		
	}
	public void setCourse_id(int id) {
		this.Course_id=id;
	}
		
	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int i) {
		this.resource_id = i;
	}

	public String getFile_Name() {
		return File_Name;
	}

	public void setFile_Name(String file_Name) {
		File_Name = file_Name;
	}

	public String getCourse_name() {
		return Course_name;
	}

	public void setCourse_name(String course_name) {
		Course_name = course_name;
	}

	public String getStart_time() {
		return Start_time;
	}

	public void setStart_time(String start_time) {
		Start_time = start_time;
	}

	public String getOver_time() {
		return Over_time;
	}

	public void setOver_time(String over_time) {
		Over_time = over_time;
	}

	public String getTea_id() {
		return Tea_id;
	}

	public void setTea_id(String tea_id) {
		Tea_id = tea_id;
	}

	public String getStu_id() {
		return Stu_id;
	}

	public void setStu_id(String stu_id) {
		Stu_id = stu_id;
	}

	public String getStu_name() {
		return Stu_name;
	}

	public void setStu_name(String stu_name) {
		Stu_name = stu_name;
	}

	public String getSubmit_time() {
		return Submit_time;
	}

	public void setSubmit_time(String submit_time) {
		Submit_time = submit_time;
	}

	
}
