package Servlet;

import java.io.IOException;
import dao.AdminDao;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import entity.Student;
import entity.Teacher;
import dao.TeacherDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	public static  String ID ;  //学生ID
	public static String Name;   //学生姓名
	public static String iden;   //身份识别令牌
    public static String Tea_id;
    public static String Tea_name;
    
    //public static String 
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Input_iden=request.getParameter("iden");  //判断身份字符
	    String Input_id=request.getParameter("name");
		String Input_pwd=request.getParameter("pass");
		
		System.out.print(Input_id+"123");
		//如果身份选择是学生
		if(Input_iden.equals("student")){
			if(!(Input_id.equals("") && Input_pwd.equals(""))){
	        StudentDao dao=new StudentDao();
	        String Real_pwd=dao.StuPwdById(Input_id);
	        
	        	if(dao.is_stuExists(Input_id)) {
	        		if(Real_pwd.equals(Input_pwd)){	       
	        		Student s=dao.queryStudentById(Input_id);
	        		request.getSession().setAttribute("Stu",s);
	        		Name=s.getStu_name();
	        		request.getSession().setAttribute("Stuid",Input_id);
	        		System.out.print(Input_id);
	        		ID=Input_id;
	        		System.out.println(ID+"THIS IS ID");
	        		String id=(String)request.getAttribute("Stuid");
	        		System.out.print(id);
	        		//out.println(s.getStu_id());
	        		//out.print("<script>alert('学生:"+Input_id+"欢迎登录'); window.location='StudentMain.jsp' </script>");
	        		PrintWriter pw=response.getWriter();
	        		pw.write("<script>alert('Student:"+Input_id+"Welcmoe'); window.location='StudentMain.jsp' </script>");
	        		}
	        	else{
	        		
	        		PrintWriter pw=response.getWriter();
	        		pw.write("<script>alert('Wrong account or password'); window.location='Login.html' </script>");
	        		response.sendRedirect(request.getHeader("Referer"));
	        		//response.sendRedirect("Customer.jsp");
	        		}
	        	}
	        	else {
	        		PrintWriter pw=response.getWriter();
	        		pw.write("<script>alert('student_number doesn't exist!'); window.location='Login.html' </script>");
	        	}
	        	
	    	}
			else {
				PrintWriter pw=response.getWriter();
	    		pw.write("<script>alert('input account or password'); window.location='Login.html' </script>");
			}
		}
		
		else if(Input_iden.equals("teacher")){
			if(!(Input_id.equals("") && Input_pwd.equals(""))){
	        TeacherDao dao=new TeacherDao();
	        String Real_pwd=dao.TeaPwdById(Input_id);
	        
	        	if(dao.is_teaExists(Input_id)) {
	        		if(Real_pwd.equals(Input_pwd)){	       
	        		Teacher s=dao.queryTeacherById(Input_id);
	        		request.getSession().setAttribute("Tea",s);
	        		Tea_name=s.getTea_name();
	        		//request.getSession().setAttribute("Stuid",Input_id);
	        		ID=Input_id;
	        		System.out.print(Input_id);
	        		Tea_id=Input_id;
	        		System.out.println(Tea_id+"THIS IS ID");
	        		//String id=(String)request.getAttribute("Stuid");
	        		//System.out.print(id);
	        		//out.println(s.getStu_id());
	        		//out.print("<script>alert('学生:"+Input_id+"欢迎登录'); window.location='StudentMain.jsp' </script>");
	        		PrintWriter pw=response.getWriter();
	        		pw.write("<script>alert('Teacher:"+Input_id+"Welcmoe'); window.location='TeacherMain.jsp' </script>");
	        		}
	        	else{
	        		
	        		PrintWriter pw=response.getWriter();
	        		pw.write("<script>alert('Wrong account or password'); window.location='Login.html' </script>");
	        		response.sendRedirect(request.getHeader("Referer"));
	        		//response.sendRedirect("Customer.jsp");
	        		}
	        	}
	        	else {
	        		PrintWriter pw=response.getWriter();
	        		pw.write("<script>alert('student_number doesn't exist!'); window.location='Login.html' </script>");
	        	}
	        	
	    	}
			else {
				PrintWriter pw=response.getWriter();
	    		pw.write("<script>alert('input account or password'); window.location='Login.html' </script>");
			}
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
