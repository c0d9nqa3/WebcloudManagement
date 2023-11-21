package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import dao.TeacherDao;

/**
 * Servlet implementation class Tea_deleteCourseServlet
 */
@WebServlet("/Tea_deleteCourseServlet")
public class Tea_deleteCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_deleteCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	String Course_id=request.getParameter("Course_id");
    	String Course_name=request.getParameter("Course_name");
    	String Class=request.getParameter("Class");
    	System.out.println("ÒªÉ¾³ýµÄ¿Î³Ìid£º"+Course_id);
    	TeacherDao tea=new TeacherDao();
    	tea.deleteCourseByID(Course_id);
    	String path = "D:/JavaEE Workspace/Homework/"+Course_name+Class;
        File file = new File(path);
        file.delete();
    	
        response.sendRedirect(request.getHeader("Referer"));

    		//pw.write("<script>alert('Successful Deleted'); window.location='Student-ViewHomeworkShow.jsp' </script>");
    	}
    	
		

		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
