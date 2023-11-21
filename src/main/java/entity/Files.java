package entity;

public class Files {
	private String id,Name,FileName;
	int resource_id;
	public Files() {
		
	}
	public Files(String id,String Name,String FileName) {
		this.setFileName(FileName);this.setName(Name);this.setId(id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public int getResource_id() {
		return resource_id;
	}
	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}
	
}
