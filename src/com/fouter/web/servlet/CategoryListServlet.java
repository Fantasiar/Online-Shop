package com.fouter.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fouter.domain.Category;
import com.fouter.service.ProductService;
import com.fouter.util.JedisPoolUtils;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/categoryList")
public class CategoryListServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductService service=new ProductService();
		Jedis jedis=JedisPoolUtils.getJedis();
		String categoryListJson = jedis.get("categoryListJson");
		if (categoryListJson==null) {
			List<Category> categoryList=service.findAllCategory();
			Gson gson=new Gson();
			categoryListJson = gson.toJson(categoryList);
			jedis.set("categoryListJson", categoryListJson);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
