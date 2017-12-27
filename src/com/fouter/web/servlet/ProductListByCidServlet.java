package com.fouter.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fouter.domain.PageBean;
import com.fouter.domain.Product;
import com.fouter.service.ProductService;

import sun.awt.datatransfer.DataTransferer.ReencodingInputStream;

/**
 * Servlet implementation class ProductListByCidServlet
 */
@WebServlet("/productListByCid")
public class ProductListByCidServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		String currentPageStr=request.getParameter("currentPage");
		if (currentPageStr==null) {
			currentPageStr="1";
		}
		int currentPage=Integer.parseInt(currentPageStr);
		int currentCount=12;
		
		ProductService service=new ProductService();
		PageBean pageBean=service.getProductListByCid(cid,currentPage,currentCount);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		List<Product> historyList=new ArrayList<Product>();
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids=cookie.getValue();
					String[] split = pids.split("-");
					for (String pid : split) {
						Product pro=service.getProductInfo(pid);
						historyList.add(pro);
					}
				}
			}
		}
		
		request.setAttribute("historyList", historyList);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
