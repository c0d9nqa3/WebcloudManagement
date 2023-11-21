package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;

/**
 * Servlet implementation class Stu_ChangePwdServlet
 */
@WebServlet("/Stu-ChangePwd")
public class Stu_ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stu_ChangePwdServlet() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentDao dao=new StudentDao();
		
		String OldPwd=request.getParameter("OldPwd");
		String NewPwd=request.getParameter("NewPwd");
		String CfmPwd=request.getParameter("CfmPwd");
		//Student s=new Student();
		//String s=((Student)session.getAttribute("Stu")).getStu_pwd();
		String id=(String)session.getAttribute("Stuid");
		System.out.print(id);
		String RealPwd=dao.queryStudentById(id).getStu_pwd();
		if(RealPwd.equals(OldPwd)) {
			if(NewPwd.equals(CfmPwd)) {
				dao.updateStudentPwd(id,NewPwd);
				PrintWriter pw=response.getWriter();
	    		pw.write("<script>alert('Edited successful!'); window.location='Student-ViewChangePwd.jsp' </script>");
			}
			else {
				PrintWriter pw=response.getWriter();
	    		pw.write("<script>alert('Check the confirmed password'); window.location='Student-ViewChangePwd.jsp' </script>");
			}
		}
		
		else {
			PrintWriter pw=response.getWriter();
    		pw.write("<script>alert('Wrong original password'); window.location='Student-ViewChangePwd.jsp' </script>");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
