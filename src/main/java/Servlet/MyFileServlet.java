package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyFileServlet
 */
@WebServlet("/MyFileServlet")
public class MyFileServlet extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFileServlet() {  
	        super();  
	    }  
 
   public void destroy() {  
       super.destroy(); // Just puts "destroy" string in log  
       // Put your code here  
   }  
 
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)  
           throws ServletException, IOException {  
       doPost(request,response);  
   }  
 
     
   public void doPost(HttpServletRequest request, HttpServletResponse response)  
           throws ServletException, IOException {  
       //��������  
       //��ȡҪ���ص��ļ�  
   		String file_name=request.getParameter("name");
   	
   		File f = new File("D:/JavaEE Workspace/Public_Resources/"+LoginServlet.ID+"/"+file_name);  
   		if(f.exists()){  
           FileInputStream  fis = new FileInputStream(f);  
           String filename=URLEncoder.encode(f.getName(),"utf-8"); //��������ļ������غ����������
           byte[] b = new byte[fis.available()];  
           fis.read(b);  
           response.setCharacterEncoding("utf-8");  
           response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
           //��ȡ��Ӧ�������������  
           ServletOutputStream  out =response.getOutputStream();  
           //���  
           out.write(b);  
           out.flush();  
           out.close();  
       }     
         
   }  
 
   /** 
    * Initialization of the servlet. <br> 
    * 
    * @throws ServletException if an error occurs 
    */  
   public void init() throws ServletException {  
       // Put your code here  
   }  

}
