package com.iteso.pae.messenger.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iteso.pae.messenger.beans.UserBean;
import com.iteso.pae.messenger.controllers.UsersController;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login...");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashed = UsersController.hashPwd(password);
		System.out.println("user["+username+"] pass:["+password+"] hashed:["+hashed+"]");
		
		List<UserBean> list = UsersController.getController().getList();
		for(UserBean user : list){
			if(user.getUsername().contentEquals(username) && user.getPassword().contentEquals(password)){
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(3 * 60);//3 min.
				response.sendRedirect("main.html");
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
        PrintWriter out= response.getWriter();
        out.println("<font color=red>Either user name or password is wrong.</font>");
        rd.include(request, response);
	}

}
