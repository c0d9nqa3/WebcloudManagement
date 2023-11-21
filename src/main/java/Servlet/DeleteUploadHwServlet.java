package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import dao.StudentDao;

/**
 * Servlet implementation class DeleteUploadHwServlet
 */
@WebServlet("/DeleteUploadHwServlet")
public class DeleteUploadHwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUploadHwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	String is_sub=request.getParameter("is_submit");
    	System.out.println(is_sub);
    	if(is_sub.equals("未提交")) {
    		 	PrintWriter pw=response.getWriter();
    		 	response.sendRedirect(request.getHeader("Referer"));

    			//pw.write("<script>alert('haven't upload yet!!'); window.location='Student-ViewHomeworkShow.jsp' </script>");
    	}
    	else if(is_sub.equals("已提交")) {
    		StudentDao stu=new StudentDao();
    		String FileName=Stu_UploadHwServlet.filename;
    		String Class=request.getParameter("Class");
    		String Stu_id=request.getParameter("Stu_id");
    		String course_name=request.getParameter("course_name");
    		int resource_id=Integer.parseInt((String)request.getParameter("resource_id"));
    		System.out.println("学生上传文件名："+FileName);
    		System.out.println("上传的学生id："+Stu_id);
    		System.out.println("resourceid："+FileName);
    		stu.stu_deleteHw(Stu_id,FileName,resource_id);
    		String path = "D:/JavaEE Workspace/Submit_homework/"+course_name+Class+"/"+FileName;
            File file = new File(path);
            file.delete();
            
            PrintWriter pw=response.getWriter();
            response.sendRedirect(request.getHeader("Referer"));

    		//pw.write("<script>alert('Successful Deleted'); window.location='Student-ViewHomeworkShow.jsp' </script>");
    	}
    	else {
    		System.out.println("判断是否提交条件未实现");
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
