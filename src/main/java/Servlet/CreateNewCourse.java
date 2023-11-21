package Servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.TeacherDao;
/**
 * Servlet implementation class CreateNewCourse
 */
@WebServlet("/CreateNewCourse")
public class CreateNewCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Course_name=request.getParameter("newCoursename");
		String Course_class=request.getParameter("newCourseclass");
		System.out.println("创建新的课程（测试创建新的文件夹）：");
		String path="D:/JavaEE Workspace/Homework/";
		String Save_Location=path+""+Course_name+""+Course_class+"//";
        new java.io.File(Save_Location).mkdir();
        TeacherDao tea=new TeacherDao();
        tea.CreateNewCourse(LoginServlet.Tea_id, LoginServlet.Tea_name, Course_name, Course_class);
        response.sendRedirect(request.getHeader("Referer"));
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
