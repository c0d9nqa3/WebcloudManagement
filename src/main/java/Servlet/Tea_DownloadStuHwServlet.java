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
 * Servlet implementation class Tea_DownloadStuHwServlet
 */
@WebServlet("/Tea_DownloadStuHwServlet")
public class Tea_DownloadStuHwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_DownloadStuHwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String file_name=request.getParameter("filename");
		String course_name=request.getParameter("course_name");
		String Class=request.getParameter("Class");
		File f = new File("D:/JavaEE Workspace/Submit_homework/"+course_name+Class+"/"+file_name);  
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
