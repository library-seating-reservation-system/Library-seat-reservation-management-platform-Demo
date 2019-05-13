package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.Util.JDBCUtil;
import com.dao.UserDao;
import com.entity.User;
import com.entity.UserAll;

public class UserService {
	UserDao u = new UserDao();

	// 查询登陆信息是否正确
	public boolean userLogin(String userName, String passWord,String level ,HttpServletRequest request) {

		User user = u.getUser(userName, passWord,level);
		if (user.getUserName() != null) {

			request.getSession().setAttribute("uq", user);

			return true;
		}
		return false;
	}

	public void adduser(String userName, String passWord,String department,String name, String grade, String profession) {
		JDBCUtil.sql = "insert into user values(null,?,?,2)";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setString(1, userName);
			JDBCUtil.ps.setString(2, passWord);
			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = iduser(userName);
		u.adduser(id,userName,name,grade,profession,department);

		
	}

	private int iduser(String userName) {
		JDBCUtil.sql = "SELECT id from  user where userName=?";
		JDBCUtil.ps = JDBCUtil.getPs();
		
		int da = 0;
		try {
			JDBCUtil.ps.setString(1, userName);
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			if(JDBCUtil.rs.next()) {
				da = JDBCUtil.rs.getInt(1);
			}
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return da;
		
	}

}