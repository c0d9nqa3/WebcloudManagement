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
 * Servlet implementation class Tea_returnHwServlet
 */
@WebServlet("/Tea_returnHwServlet")
public class Tea_returnHwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_returnHwServlet() {
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
		String resource_id=request.getParameter("resource_id");
		String Stu_id=request.getParameter("Stu_id");
		TeacherDao tea=new TeacherDao();
		tea.Tea_returnHomework(Integer.parseInt(resource_id),Stu_id);
		String path = "D:/JavaEE Workspace/Submit_homework/"+course_name+Class+"/"+file_name;
        File file = new File(path);
        file.delete();
        response.sendRedirect(request.getHeader("Referer"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
