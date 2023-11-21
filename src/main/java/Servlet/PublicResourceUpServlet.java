package Servlet;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Servlet.AdminLoginServlet;
import Servlet.Stu_UploadHwServlet.Position;
import dao.AdminDao;
import dao.StudentDao;

/**
 * Servlet implementation class PublicResourceUpServlet
 */
@WebServlet("/PublicResourceUpServlet")
public class PublicResourceUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String filename_pub;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public PublicResourceUpServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
		      throws ServletException, IOException { 
    	
			  request.setCharacterEncoding("UTF-8");
		        response.setCharacterEncoding("UTF-8");
				//java.util.Dateת��ΪString 	 
		    response.setContentType("text/html;charset=UTF-8"); 
		    //��ȡ����Body 
		    byte[] body = readBody(request); 
		    //ȡ������Body���ݵ��ַ�����ʾ 
		    String textBody = new String(body, "UTF-8"); 
		    //ȡ���ϴ����ļ����� 
		    String fileName = getFileName(textBody); 
		    filename_pub=fileName;
		    System.out.println("����Ա�ϴ���"+filename_pub);
		    //ȡ���ļ���ʼ�����λ�� 
		    Position p = getFilePosition(request, textBody); 
		    //������ļ� 
		    writeTo(fileName, body, p);  
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
    	String path = null;
    	String type=AdminLoginServlet.File_type;
    	System.out.println("��ǰ�ļ������ǣ�"+type);
    	if(type.equals("stu")) {
    		path="D:/JavaEE Workspace/OpenResources/student_work/";
    	}
    	else if(type.equals("table")) {
    		path="D:/JavaEE Workspace/OpenResources/submit_table/";
    	}
    	else if(type.equals("rule")) {
    		path="D:/JavaEE Workspace/OpenResources/rules/";
    	} 
    	else if(type.equals("video")) {
    		path="D:/JavaEE Workspace/OpenResources/video/";
    	}

		 // request.getAttribute("key");
	    FileOutputStream fileOutputStream = new FileOutputStream(path+fileName); 
	    fileOutputStream.write(body, p.begin, (p.end - p.begin)); 
	    fileOutputStream.flush(); 
	    fileOutputStream.close(); 
	  } 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		AdminDao admin=new AdminDao();
       
    processRequest(request, response); 
    String type=AdminLoginServlet.File_type;
    if(type.equals("stu")) {
		admin.create_Student_workfile(filename_pub);
	}
	else if(type.equals("table")) {
		admin.create_table(filename_pub);
	}
	else if(type.equals("rule")) {
		admin.create_rule(filename_pub);
	}
	else if(type.equals("video")) {
		admin.create_video(filename_pub);
		
	}
    PrintWriter pw=response.getWriter();
	//pw.write("<script>alert('Successful Upload'); window.location='Student-ViewUpHw.jsp' </script>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.sendRedirect(request.getHeader("Referer"));
	}

}
