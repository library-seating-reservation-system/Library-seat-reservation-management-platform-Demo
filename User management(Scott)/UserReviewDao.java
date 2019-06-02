package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.Util.JDBCUtil;
import com.entity.UserAll;

public class UserReviewDao {

	public List<UserAll> queryAll() {
		List<UserAll> list = new ArrayList<>();
		JDBCUtil.sql = "SELECT * from  user RIGHT JOIN message ON user.id=message.id";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			while (JDBCUtil.rs.next()) {
				UserAll u = new UserAll();
				u.setUserName(JDBCUtil.rs.getString(2));
				u.setPassWord(JDBCUtil.rs.getString(3));
				u.setName(JDBCUtil.rs.getString(6));
				u.setDepartment(JDBCUtil.rs.getString(7));
				u.setGrade(JDBCUtil.rs.getString(8));
				u.setProfession(JDBCUtil.rs.getString(9));
				u.setTime(JDBCUtil.rs.getDate(10));
				list.add(u);
			}
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void remove(String id) {
		// TODO Auto-generated method stub
				JDBCUtil.sql = "delete from message where id=?";
				JDBCUtil.ps = JDBCUtil.getPs();
				try {
					JDBCUtil.ps.setString(1, id);
					JDBCUtil.ps.executeUpdate();
					JDBCUtil.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
	}

}
