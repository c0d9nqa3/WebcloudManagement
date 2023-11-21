package Servlet;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Servlet.Stu_UploadHwServlet.Position;
import dao.StudentDao;
import dao.TeacherDao;

/**
 * Servlet implementation class Tea_createNewHwServlet
 */
@MultipartConfig
@WebServlet("/Tea_createNewHwServlet")
public class Tea_createNewHwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	String datetime = format.format(date); //2013-01-14
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	String Class;
	String Course_name;
	int resource_id;
    public Tea_createNewHwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    public static String filename;
	
	  protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
	      throws ServletException, IOException { 
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		//Tea_id=(String) request.getSession().getAttribute("Tea_id");
		Class=(String) request.getSession().getAttribute("Class");
		Course_name=(String) request.getSession().getAttribute("Course_name");
		//resource_id=(int) request.getSession().getAttribute("resource_id");
			//java.util.Date转换为String 	 
		//System.out.println(Tea_id);
		System.out.println(Class);
		System.out.println(Course_name);
		//.out.println(resource_id);
		System.out.println(datetime);
		   //取得文件名
        Part part = request.getPart("uploadFile");
        String fileName = getFileName(part);
	    //输出至文件 
        filename=fileName;
        System.out.println("上传的作业文件夹名称是："+filename);
	    writeTo(fileName,part);  
	  } 
	  
	  //构造类 
	  class Position { 
	  
	    int begin; 
	    int end; 
	  
	    public Position(int begin, int end) { 
	      this.begin = begin; 
	      this.end = end; 
	    } 
	  } 
	  
	    //取得上传文件名
	    private String getFileName(Part part) {
	        String header = part.getHeader("Content-Disposition");
	        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));

	        return fileName;
	    }
	  
	  
	  /*
	   
	  
	  private String getFileName(String requestBody) { 
	    String fileName = requestBody.substring(requestBody.indexOf("filename=\"") + 10); 
	    fileName = fileName.substring(0, fileName.indexOf("\n")); 
	    fileName = fileName.substring(fileName.indexOf("\n") + 1, fileName.indexOf("\"")); 
	    return fileName; 
	  } 
	  */
	  
	  private void writeTo(String fileName, Part part) throws IOException { 
		  	InputStream in = part.getInputStream();
	        OutputStream out = new FileOutputStream("D:/JavaEE Workspace/Homework/"+Course_name+Class+"/"+fileName);
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = in.read(buffer)) != -1) {
	            out.write(buffer, 0, length);
	        }

	        in.close();
	        out.close();  

		 // request.getAttribute("key");
	    //FileOutputStream fileOutputStream = new FileOutputStream("D:/JavaEE Workspace/Homework/"+Course_name+Class+"/"+fileName); 
	    
	  } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		String Over_time=request.getParameter("HomeworkEnddate"); 
		System.out.println("设置的作业结束时间"+Over_time);
		String Class=(String) request.getSession().getAttribute("Class");
		int Course_id=Integer.parseInt((String) request.getSession().getAttribute("Course_id"));
		System.out.println(Course_id);
		//String Over_time=request.getParameter("HomeworkEnddate");
		String Course_name=(String) request.getSession().getAttribute("Course_name");
		//int resource_id=Integer.parseInt((String) request.getSession().getAttribute("resource_id"));
		//java.util.Date转换为String 	 
		Date date = new Date(); 	 
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 	 
		String datetime = format.format(date); 
		
		TeacherDao tea=new TeacherDao();
		
		processRequest(request, response); 
		tea.Tea_newHomework(filename, Course_name,Class,datetime,Over_time,LoginServlet.Tea_id,Course_id);
		PrintWriter pw=response.getWriter();
		response.sendRedirect(request.getHeader("Referer"));
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		String Over_time=request.getParameter("HomeworkEnddate");
		System.out.println("post设置的作业结束时间"+Over_time);
		doGet(request, response);
	}
	 @Override
	  public String getServletInfo() { 
	    return "Short description"; 
	  }

}
