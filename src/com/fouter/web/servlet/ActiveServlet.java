package com.fouter.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fouter.domain.User;
import com.fouter.service.UserService;

/**
 * Servlet implementation class ActiveServlet
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("activeCode");
		UserService service=new UserService();
		boolean isActiveSuccess=service.setActive(code);
		if (isActiveSuccess) {
			response.sendRedirect("http://localhost:8080/shop/login.jsp");
		}else {
			response.getWriter().write("sorry,you fail!");
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
