package Servlet;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import dao.StudentDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	
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
	  protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
	      throws ServletException, IOException { 
	    response.setContentType("UTF-8"); 
	    //��ȡ����Body 
	    byte[] body = readBody(request); 
	    //ȡ������Body���ݵ��ַ�����ʾ 
	    String textBody = new String(body, "UTF-8"); 
	    //ȡ���ϴ����ļ����� 
	    String fileName = getFileName(textBody); 
	    //ȡ���ļ���ʼ�����λ�� 
	    Position p = getFilePosition(request, textBody); 
	    //������ļ� 
	    writeTo(fileName, body, p); 
	    StudentDao stu=new StudentDao();
	    stu.StuUploadFileInfo(LoginServlet.ID,LoginServlet.Name,fileName);
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
	    //��ȡ�����ı��ֽڳ��� 
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
	    FileOutputStream fileOutputStream = new FileOutputStream("D:/JavaEE Workspace/Public_Resources/"+LoginServlet.ID+"/"+fileName); 
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
	    processRequest(request, response); 
	    PrintWriter pw=response.getWriter();
		pw.write("<script>alert('Successful Upload'); window.location='Student-ViewUpload.jsp' </script>");
	    
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
	    processRequest(request, response); 
	    PrintWriter pw=response.getWriter();
		pw.write("<script>alert('Successful Upload'); window.location='Student-ViewUpload.jsp' </script>");	    
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
