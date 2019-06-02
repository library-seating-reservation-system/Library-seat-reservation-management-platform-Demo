package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.Util.JDBCUtil;
import com.entity.Seat;

public class SpareSeatDao {

	public List<Seat> queryAll() {
		List<Seat> list = new ArrayList<>();
		JDBCUtil.sql = "select * from information where have=0";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			while (JDBCUtil.rs.next()) {
				Seat s = new Seat();
				s.setFloor(JDBCUtil.rs.getString(2));
				s.setSeatNumber(JDBCUtil.rs.getString(3));
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

	public void add(Integer id, String id2, String name) {
		// TODO Auto-generated method stub
		
		JDBCUtil.sql = "UPDATE information set have=1,whoid=?,whoname=? where seatNumber=?;";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			
			JDBCUtil.ps.setString(1,id+"");
			
			JDBCUtil.ps.setString(2, name);
			JDBCUtil.ps.setString(3, id2);
		
			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adderr(Integer id, String uname, String name, String seatnumber, String date) {
				JDBCUtil.sql = "insert into seaterr values(null,?,?,?,?,?)";
				JDBCUtil.ps = JDBCUtil.getPs();
				try {
					JDBCUtil.ps.setString(1, name);
					JDBCUtil.ps.setString(2, seatnumber);
					JDBCUtil.ps.setString(3, date);
					JDBCUtil.ps.setString(4,id+"");
					JDBCUtil.ps.setString(5, uname);
					JDBCUtil.ps.executeUpdate();
					JDBCUtil.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SeatErr(seatnumber);
		
	}

	private void SeatErr(String seatnumber) {
		JDBCUtil.sql = "UPDATE information set have=3 where seatNumber=?;";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			
			JDBCUtil.ps.setString(1,seatnumber);
			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
