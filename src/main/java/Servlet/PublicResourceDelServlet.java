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

import dao.AdminDao;

/**
 * Servlet implementation class PublicResourceDelServlet
 */
@WebServlet("/PublicResourceDelServlet")
public class PublicResourceDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicResourceDelServlet() {
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
			String id=request.getParameter("id");
			String file_name=request.getParameter("name");
			File f = new File("D:/JavaEE Workspace/Open_Resources/rules/"+file_name);  
	   		f.delete();
	   		AdminDao admin=new AdminDao();
	   		admin.Admin_deleteRule(Integer.parseInt(id));
	   		response.sendRedirect(request.getHeader("Referer"));
	   				
		}
		else if(type.equals("table")) {
			String id=request.getParameter("id");
			String file_name=request.getParameter("name");
			File f = new File("D:/JavaEE Workspace/Open_Resources/submit_table/"+file_name);  
	   		f.delete();
	   		AdminDao admin=new AdminDao();
	   		admin.Admin_deleteTable(Integer.parseInt(id));
	   		response.sendRedirect(request.getHeader("Referer"));
	   				
		}
		else if(type.equals("stu")) {
			String id=request.getParameter("id");
			String file_name=request.getParameter("name");
			File f = new File("D:/JavaEE Workspace/Open_Resources/student_work/"+file_name);  
	   		f.delete();
	   		AdminDao admin=new AdminDao();
	   		admin.Admin_deleteStudentWorkFile(Integer.parseInt(id));
	   		response.sendRedirect(request.getHeader("Referer"));
	   				
		}
		else if(type.equals("video")) {
			String id=request.getParameter("id");
			String file_name=request.getParameter("name");
			File f = new File("D:/JavaEE Workspace/Open_Resources/video/"+file_name);  
	   		f.delete();
	   		AdminDao admin=new AdminDao();
	   		admin.Admin_deleteVideo(Integer.parseInt(id));
	   		response.sendRedirect(request.getHeader("Referer"));
	   				
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
