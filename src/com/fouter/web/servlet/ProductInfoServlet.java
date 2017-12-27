package com.fouter.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSException;

import com.fouter.domain.Product;
import com.fouter.service.ProductService;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Servlet implementation class ProductInfoServlet
 */
@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid=request.getParameter("cid");
		String currentPage=request.getParameter("currentPage");
		String pid = request.getParameter("pid");
		String pids=pid;
		ProductService service=new ProductService();
		Product product=service.getProductInfo(pid);
		
		request.setAttribute("product", product);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					pids=cookie.getValue();
					String[] split = pids.split("-");
					List<String> asList = Arrays.asList(split);
					LinkedList<String> list=new LinkedList<String>(asList);
					if (list.contains(pid)) {
						list.remove(pid);
					}
					list.addFirst(pid);
					StringBuffer sBuffer=new StringBuffer();
					for (int i = 0; i < list.size(); i++) {
						sBuffer.append(list.get(i));
						sBuffer.append("-");
					}
					
					pids=sBuffer.substring(0, sBuffer.length()-1);
					
					
				}
			}	
		}
		
		Cookie pids_cookie=new Cookie("pids", pids);
		
		response.addCookie(pids_cookie);
		
		
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
