package com.fouter.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import com.fouter.domain.User;
import com.fouter.util.DataSourceUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserDao {

	public int regist(User user) throws SQLException {
		
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int row = qRunner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return row;
	}

	public int setActive(String code) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set state=1 where code=?";
		int row=qRunner.update(sql,code);
		return row;
	}

	public Long checkUsername(String username) throws SQLException {
	
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from user where username=?";
		Long num=(Long) qRunner.query(sql, new ScalarHandler(),username);
		return num;
	}

}
