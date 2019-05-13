package com.service;

import java.util.List;

import com.Util.JDBCUtil;
import com.dao.UserReviewDao;
import com.entity.Seat;
import com.entity.UserAll;

public class UserReviewService {
UserReviewDao ud = new UserReviewDao();
	public List<UserAll> queryAll() {
		 List<UserAll> list = ud.queryAll();
			return list;
	}
	public void remove(String id) {
		// TODO Auto-generated method stub
		JDBCUtil.sql = "delete from user where id=?";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setString(1, id);
			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCUtil.close();
		
		ud.remove(id);
		
	}

}
