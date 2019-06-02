package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.Util.JDBCUtil;
import com.entity.Seat;

public class MySeatDao {

	public List<Seat> queryAll(Integer id) {
		List<Seat> list = new ArrayList<>();
		JDBCUtil.sql = "select * from information where whoid=?";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setInt(1, id);
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			while(JDBCUtil.rs.next()) {
				Seat s = new Seat();
				s.setFloor(JDBCUtil.rs.getString(2));
				s.setSeatNumber(JDBCUtil.rs.getString(3));
				System.out.println(s.getSeatNumber());
				s.setDate(JDBCUtil.rs.getDate(4));
				s.setWhoName(JDBCUtil.rs.getString(7));
				list.add(s);
			}
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	// 移除个人占位
	public void delete(String seatNumber) {
		JDBCUtil.sql = "UPDATE information set have=0,whoid=null,whoname=null where seatNumber=?;";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setString(1, seatNumber);

			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
