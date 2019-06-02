package com.dao;

import com.Util.JDBCUtil;
import com.Util.NewTime;
import com.entity.User;


public class UserDao {
    //查询用户
	public User getUser(String userName,String passWord,String level) {
        User u = new User();
     		JDBCUtil.sql = "select * from user where username=? and password=? and level=?";
     		JDBCUtil.ps = JDBCUtil.getPs();
     		try {
     			JDBCUtil.ps.setString(1, userName);
     			JDBCUtil.ps.setString(2, passWord);
     			JDBCUtil.ps.setString(3, level);
     			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
     			if (JDBCUtil.rs.next()) {
     				u.setId(JDBCUtil.rs.getInt(1));
     				u.setUserName(JDBCUtil.rs.getString(2));
     				u.setPassWord(JDBCUtil.rs.getString(3));
     				u.setLevel(JDBCUtil.rs.getString(4));
     			}
     			JDBCUtil.close();
     		} catch (Exception e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        return u;
	}

	public void adduser(int a,String userName, String name, String grade, String profession, String department) {
		JDBCUtil.sql = "insert into message values(?,?,?,?,?,?)";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setInt(1, a);
			JDBCUtil.ps.setString(2, name);
			JDBCUtil.ps.setString(3, department);
			JDBCUtil.ps.setString(4, grade);
			JDBCUtil.ps.setString(5, profession);
			JDBCUtil.ps.setString(6, new NewTime().LoginTime());
			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
