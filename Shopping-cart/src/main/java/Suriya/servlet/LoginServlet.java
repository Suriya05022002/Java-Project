package Suriya.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import Suriya.connection.Dbcon;
import Suriya.dao.UserDao;
import Suriya.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter())
		{
			String email=request.getParameter("login-email");
			String password=request.getParameter("login-password");
	 
			try
			{
			UserDao udao=new UserDao(Dbcon.getConnection());
		User user= (User) udao.userLogin(email,password);
		if(user!=null)
		{
			out.print("user login failed"); 
			request.getSession().setAttribute("auth",user);
			response.sendRedirect("index.jsp");
		}
			}
			catch(ClassNotFoundException|SQLException e)
			{
				((Throwable) e).printStackTrace();
			}
			
			out.print(email+password);
		}
	}
}
