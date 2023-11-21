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
 * Servlet implementation class AdminChangePwd
 */
@WebServlet("/AdminChangePwd")
public class AdminChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangePwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String OldPwd=request.getParameter("OldPwd");
		String NewPwd=request.getParameter("NewPwd");
		String CfmPwd=request.getParameter("CfmPwd");
		AdminDao admin=new AdminDao();
		//Student s=new Student();
		//String s=((Student)session.getAttribute("Stu")).getStu_pwd();
		
		System.out.print("test:要修改的管理员id是："+AdminLoginServlet.Admin_id);
		String RealPwd=admin.adminPwdById(AdminLoginServlet.Admin_id);
		if(RealPwd.equals(OldPwd)) {
			if(NewPwd.equals(CfmPwd)) {
				admin.updateAdminPwd(AdminLoginServlet.Admin_id,NewPwd);
				PrintWriter pw=response.getWriter();
	    		pw.write("<script>alert('Edited successful!'); window.location='AdminChangePwd.jsp' </script>");
			}
			else {
				PrintWriter pw=response.getWriter();
	    		pw.write("<script>alert('Check the confirmed password'); window.location='AdminChangePwd.jsp' </script>");
			}
		}
		
		else {
			PrintWriter pw=response.getWriter();
    		pw.write("<script>alert('Wrong original password'); window.location='AdminChangePwd.jsp' </script>");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
