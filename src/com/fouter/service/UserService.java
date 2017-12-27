package com.fouter.service;

import java.sql.SQLException;

import com.fouter.dao.UserDao;
import com.fouter.domain.User;

public class UserService {

	public boolean regist(User user) {
		
		UserDao dao=new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row>0?true:false;
	}

	public boolean setActive(String code) {
		// TODO Auto-generated method stub
		UserDao dao=new UserDao();
		int row=0;
		try {
			row=dao.setActive(code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}

	public Boolean checkUsername(String username) {
		UserDao dao=new UserDao();
		Long num = null;
		try {
			num = dao.checkUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num>0?true:false;
	}

}
