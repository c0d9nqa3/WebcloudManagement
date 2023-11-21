package Servlet;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import Servlet.UploadServlet.Position;
import dao.StudentDao;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Stu_UploadHwServlet
 */
@WebServlet("/Stu_UploadHwServlet")
public class Stu_UploadHwServlet extends HttpServlet {
	/** 
	   * Processes requests for both HTTP 
	   * <code>GET</code> and 
	   * <code>POST</code> methods. 
	   * 
	   * @param request servlet request 
	   * @param response servlet response 
	   * @throws ServletException if a servlet-specific error occurs 
	   * @throws IOException if an I/O error occurs 
	   */
	//HttpServletRequest request;
		String Stu_id;
		String Class;
		String Course_name;
		int resource_id;
		Date date = new Date(); 	 
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		String datetime = format.format(date); //2013-01-14
		public static String filename;
	
	  protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
	      throws ServletException, IOException { 
		  request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
		Stu_id=(String) request.getSession().getAttribute("Stu_id");
		Class=(String) request.getSession().getAttribute("Class");
		Course_name=(String) request.getSession().getAttribute("Course_name");
		resource_id=Integer.parseInt((String) request.getSession().getAttribute("resource_id"));
			//java.util.Dateת��ΪString 	 
		System.out.println(Stu_id);
		System.out.println(Class);
		System.out.println(Course_name);
		System.out.println(resource_id);
		System.out.println(datetime);
	    response.setContentType("text/html;charset=UTF-8"); 
	    //��ȡ����Body 
	    byte[] body = readBody(request); 
	    //ȡ������Body���ݵ��ַ�����ʾ 
	    String textBody = new String(body, "UTF-8"); 
	    //ȡ���ϴ����ļ����� 
	    String fileName = getFileName(textBody); 
	    filename=fileName;
	    System.out.println("ѧ���ϴ�����ҵ��Ϊ��"+filename);
	    //ȡ���ļ���ʼ�����λ�� 
	    Position p = getFilePosition(request, textBody); 
	    //������ļ� 
	    writeTo(fileName, body, p);  
	  } 
	  
	  //������ 
	  class Position { 
	  
	    int begin; 
	    int end; 
	  
	    public Position(int begin, int end) { 
	      this.begin = begin; 
	      this.end = end; 
	    } 
	  } 
	  
	  private byte[] readBody(HttpServletRequest request) throws IOException { 
	    
		request.setCharacterEncoding("UTF-8");
	       // response.setCharacterEncoding("UTF-8");//��ȡ�����ı��ֽڳ��� 
	    int formDataLength = request.getContentLength(); 
	    //ȡ��ServletInputStream���������� 
	    DataInputStream dataStream = new DataInputStream(request.getInputStream()); 
	    byte body[] = new byte[formDataLength]; 
	    int totalBytes = 0; 
	    while (totalBytes < formDataLength) { 
	      int bytes = dataStream.read(body, totalBytes, formDataLength); 
	      totalBytes += bytes; 
	    } 
	    return body; 
	  } 
	  
	  private Position getFilePosition(HttpServletRequest request, String textBody) throws IOException { 
		request.setCharacterEncoding("UTF-8");      
	    //ȡ���ļ����α߽���Ϣ 
	    String contentType = request.getContentType(); 
	    String boundaryText = contentType.substring(contentType.lastIndexOf("=") + 1, contentType.length()); 
	    //ȡ��ʵ���ϴ��ļ������������λ�� 
	    int pos = textBody.indexOf("filename=\""); 
	    pos = textBody.indexOf("\n", pos) + 1; 
	    pos = textBody.indexOf("\n", pos) + 1; 
	    pos = textBody.indexOf("\n", pos) + 1; 
	    int boundaryLoc = textBody.indexOf(boundaryText, pos) - 4; 
	    int begin = ((textBody.substring(0, pos)).getBytes("ISO-8859-1")).length; 
	    int end = ((textBody.substring(0, boundaryLoc)).getBytes("ISO-8859-1")).length; 
	    return new Position(begin, end); 
	  } 
	  
	  private String getFileName(String requestBody) { 
	    String fileName = requestBody.substring(requestBody.indexOf("filename=\"") + 10); 
	    fileName = fileName.substring(0, fileName.indexOf("\n")); 
	    fileName = fileName.substring(fileName.indexOf("\n") + 1, fileName.indexOf("\"")); 
	    return fileName; 
	  } 
	  
	  private void writeTo(String fileName, byte[] body, Position p) throws IOException { 
		 // request.getAttribute("key");
	    FileOutputStream fileOutputStream = new FileOutputStream("D:/JavaEE Workspace/Submit_homework/"+Course_name+Class+"/"+fileName); 
	    fileOutputStream.write(body, p.begin, (p.end - p.begin)); 
	    fileOutputStream.flush(); 
	    fileOutputStream.close(); 
	  } 
	  
	  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."> 
	  /** 
	   * Handles the HTTP 
	   * <code>GET</code> method. 
	   * 
	   * @param request servlet request 
	   * @param response servlet response 
	   * @throws ServletException if a servlet-specific error occurs 
	   * @throws IOException if an I/O error occurs 
	   */
	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws ServletException, IOException { 
		  request.setCharacterEncoding("UTF-8");
	      response.setCharacterEncoding("text/html; charset=UTF-8");
		  String Stu_id=(String) request.getSession().getAttribute("Stu_id");
		  String Class=(String) request.getSession().getAttribute("Class");
		  String Course_name=(String) request.getSession().getAttribute("Course_name");
		  int resource_id=Integer.parseInt((String) request.getSession().getAttribute("resource_id"));
		  //java.util.Dateת��ΪString 	 
		  Date date = new Date(); 	 
		  DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 	 
		  String datetime = format.format(date); //2013-01-14
		  processRequest(request, response); 
		  StudentDao stu=new StudentDao();
		  stu.StuUploadHwInfo(Stu_id, resource_id,LoginServlet.Name, filename, datetime,"���ύ");
		  PrintWriter pw=response.getWriter();
		  response.sendRedirect(request.getHeader("Referer"));
		//pw.write("<script>alert('Successful Upload'); window.location='Student-ViewUpHw.jsp' </script>");
	    
	  } 
	  
	  /** 
	   * Handles the HTTP 
	   * <code>POST</code> method. 
	   * 
	   * @param request servlet request 
	   * @param response servlet response 
	   * @throws ServletException if a servlet-specific error occurs 
	   * @throws IOException if an I/O error occurs 
	   */
	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws ServletException, IOException { 
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    processRequest(request, response); 
	    StudentDao stu=new StudentDao();
	    stu.StuUploadHwInfo(Stu_id, resource_id, LoginServlet.Name,filename,datetime,"���ύ");
	    PrintWriter pw=response.getWriter();
	    response.sendRedirect(request.getHeader("Referer"));
		//pw.write("<script>alert('Successful Upload'); window.location='Student-ViewUpHw.jsp' </script>");	    
	  } 
	  
	  /** 
	   * Returns a short description of the servlet. 
	   * 
	   * @return a String containing servlet description 
	   */
	  @Override
	  public String getServletInfo() { 
	    return "Short description"; 
	  }// </editor-fold> 
	  

}
