package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String Admin_id;
	public static String File_type;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Input_id=request.getParameter("adminid");
		String Input_pwd=request.getParameter("adminpwd");
		
			if(!(Input_id.equals("") && Input_pwd.equals(""))){
		        AdminDao admin=new AdminDao();
		        String Real_pwd=admin.adminPwdById(Input_id);
		        
		        	if(admin.is_AdminExists(Input_id)) {
		        		if(Real_pwd.equals(Input_pwd)){	       
		        		//Teacher t=tea.queryTeacherById(Input_id);
		        		//request.getSession().setAttribute("Tea",t);
		        		Admin_id=Input_id;
		        		//request.getSession().setAttribute("Tea_id",Input_id);
		        		System.out.print(Input_id);
		        		//Tea_id=Input_id;
		        		//Name=t.getTea_name();
		        		System.out.println(Admin_id+"THIS IS ADMIN ID");
		        		//System.out.println(ID+"THIS IS ID");
		        		//String id=(String)request.getAttribute("Stuid");
		        		//System.out.print(id);
		        		//out.println(s.getStu_id());
		        		//out.print("<script>alert('Ñ§Éú:"+Input_id+"»¶Ó­µÇÂ¼'); window.location='StudentMain.jsp' </script>");
		        		PrintWriter pw=response.getWriter();
		        		pw.write("<script>alert('ADMIN:"+Input_id+"Welcmoe'); window.location='AdminMain.jsp' </script>");
		        		}
		        	else{
		        		response.sendRedirect(request.getHeader("Referer"));
		        		PrintWriter pw=response.getWriter();
		        		pw.write("<script>alert('Wrong account or password'); window.location='Login.html' </script>");
		        		response.sendRedirect(request.getHeader("Referer"));
		        		//response.sendRedirect("Customer.jsp");
		        		}
		        	}
		        	else {
		        		PrintWriter pw=response.getWriter();
		        		pw.write("<script>alert('admin doesn't exist!'); window.location='Login.html' </script>");
		        	}
		        	
		    	}
			else {
				PrintWriter pw=response.getWriter();
        		pw.write("<script>alert('wrong!'); window.location='Login.html' </script>");
			}
			
				
			
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
