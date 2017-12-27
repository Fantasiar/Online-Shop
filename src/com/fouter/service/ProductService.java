package com.fouter.service;

import java.sql.SQLException; 
import java.util.List;

import com.fouter.dao.ProductDao;
import com.fouter.domain.Category;
import com.fouter.domain.PageBean;
import com.fouter.domain.Product;

public class ProductService {

	public List<Product> findHotProduct() {
		// TODO Auto-generated method stub
		ProductDao dao=new ProductDao();
		List<Product> hotProductList = null;
		try {
			hotProductList = dao.findHotProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotProductList;
	}

	public List<Product> findNewProduct() {
		// TODO Auto-generated method stub
		ProductDao dao=new ProductDao();
		List<Product> newProductList = null;
		try {
			newProductList = dao.findNewProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newProductList;
	}

	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		ProductDao dao=new ProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}

	public PageBean getProductListByCid(String cid, int currentPage, int currentCount) {
		// TODO Auto-generated method stub
		ProductDao dao=new ProductDao();
		
		PageBean<Product> pageBean=new PageBean<Product>();
		
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		
		int totalCount=0;
		
		try {
			totalCount=dao.getCount(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pageBean.setTotalCount(totalCount);
		
		int totalPage=(int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		int index=(currentPage-1)*currentCount;
		
		List<Product> productList = null;
		try {
			productList=dao.getProductListByCid(cid,index,currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pageBean.setList(productList);
		return pageBean;
	}

	public Product getProductInfo(String pid) {
		// TODO Auto-generated method stub
		ProductDao dao=new ProductDao();
		Product product = null;
		try {
			product = dao.getProductInfo(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

}
