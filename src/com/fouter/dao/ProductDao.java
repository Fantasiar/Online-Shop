package com.fouter.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.fouter.domain.Category;
import com.fouter.domain.Product;
import com.fouter.util.DataSourceUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ProductDao {

	public List<Product> findHotProduct() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where is_hot=? limit ?,?";
		List<Product> hotProductList = qRunner.query(sql, new BeanListHandler<Product>(Product.class),1,0,9);
		return hotProductList;
	}

	public List<Product> findNewProduct() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product order by pdate limit ?,?";
		List<Product> newProductList = qRunner.query(sql, new BeanListHandler<Product>(Product.class),0,9);
		return newProductList;
	}

	public List<Category> findAllCategory() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		List<Category> categoryList=qRunner.query(sql,new BeanListHandler<Category>(Category.class));
		return categoryList;
	}


	public int getCount(String cid) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product where cid=?";
		Long totalCount=(Long) qRunner.query(sql,new ScalarHandler(),cid);
		return totalCount.intValue();
	}

	public List<Product> getProductListByCid(String cid, int index, int currentCount) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where cid=? limit ?,?";
		List<Product> productList=qRunner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,currentCount);
		return productList;
	}

	public Product getProductInfo(String pid) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pid=?";
		Product product=qRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

}
