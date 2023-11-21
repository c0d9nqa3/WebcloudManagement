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
 * Servlet implementation class PublicResourceDownServlet
 */
@WebServlet("/PublicResourceDownServlet")
public class PublicResourceDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicResourceDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		
		if(type.equals("rule")) {
			String file_name=request.getParameter("name");
			File f = new File("D:/JavaEE Workspace/rules/"+file_name);  
	   		if(f.exists()){  
	           FileInputStream  fis = new FileInputStream(f);  
	           String filename=URLEncoder.encode(f.getName(),"utf-8"); //解决中文文件名下载后乱码的问题
	           byte[] b = new byte[fis.available()];  
	           fis.read(b);  
	           response.setCharacterEncoding("utf-8");  
	           response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
	           //获取响应报文输出流对象  
	           ServletOutputStream  out =response.getOutputStream();  
	           //输出  
	           out.write(b);  
	           out.flush();  
	           out.close();  		
	   		}
		}
		
		else if(type.equals("table")) {
			String file_name=request.getParameter("name");
			File f = new File("D:/JavaEE Workspace/submit_table/"+file_name);  
	   		if(f.exists()){  
	           FileInputStream  fis = new FileInputStream(f);  
	           String filename=URLEncoder.encode(f.getName(),"utf-8"); //解决中文文件名下载后乱码的问题
	           byte[] b = new byte[fis.available()];  
	           fis.read(b);  
	           response.setCharacterEncoding("utf-8");  
	           response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
	           //获取响应报文输出流对象  
	           ServletOutputStream  out =response.getOutputStream();  
	           //输出  
	           out.write(b);  
	           out.flush();  
	           out.close();  		
	   		}
		}
		else if(type.equals("stu")) {
			String file_name=request.getParameter("name");
			File f = new File("D:/JavaEE Workspace/student_work/"+file_name);  
	   		if(f.exists()){  
	           FileInputStream  fis = new FileInputStream(f);  
	           String filename=URLEncoder.encode(f.getName(),"utf-8"); //解决中文文件名下载后乱码的问题
	           byte[] b = new byte[fis.available()];  
	           fis.read(b);  
	           response.setCharacterEncoding("utf-8");  
	           response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
	           //获取响应报文输出流对象  
	           ServletOutputStream  out =response.getOutputStream();  
	           //输出  
	           out.write(b);  
	           out.flush();  
	           out.close();  		
	   		}
		}
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
