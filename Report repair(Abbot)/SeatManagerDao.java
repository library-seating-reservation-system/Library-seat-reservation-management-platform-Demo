package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.Util.JDBCUtil;
import com.entity.MSeat;
import com.entity.Seat;

public class SeatManagerDao {
	public List<MSeat> queryAll() {
		List<MSeat> list = new ArrayList<>();
		JDBCUtil.sql = "select * from information";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			while (JDBCUtil.rs.next()) {
				MSeat s = new MSeat();
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

	public void edit(String seatnumbner, String oper,String id,String data,String Floor) {
		// TODO Auto-generated method stub
		System.out.println("----------");
		System.out.println(seatnumbner);
		System.out.println(oper);
		System.out.println(id);
		
		
		if(id.equals(null)||id==""){
			JDBCUtil.sql = "UPDATE information set have=0,floor=?,time=?,whoid=null,whoname=null where seatNumber=?;";
			JDBCUtil.ps = JDBCUtil.getPs();
			try {
				JDBCUtil.ps.setString(1,Floor);
				JDBCUtil.ps.setString(2, data);
				JDBCUtil.ps.setString(3, seatnumbner);
				JDBCUtil.ps.executeUpdate();
				JDBCUtil.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			JDBCUtil.sql = "UPDATE information set have=1,floor=?,time=?,whoid=?,whoname=? where seatNumber=?;";
			JDBCUtil.ps = JDBCUtil.getPs();
			try {
				JDBCUtil.ps.setString(1,Floor);
				JDBCUtil.ps.setString(2, data);
				JDBCUtil.ps.setString(3,id);
				JDBCUtil.ps.setString(4, oper);
				JDBCUtil.ps.setString(5, seatnumbner);
				JDBCUtil.ps.executeUpdate();
				JDBCUtil.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

	public List<Seat> queryAllerr() {
		List<Seat> list = new ArrayList<>();
		JDBCUtil.sql = "select * from seaterr";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			while(JDBCUtil.rs.next()) {
				Seat s = new Seat();
				s.setFloor(JDBCUtil.rs.getString(2));
				s.setSeatNumber(JDBCUtil.rs.getString(3));
				s.setDate(JDBCUtil.rs.getDate(4));
				s.setWhoName(JDBCUtil.rs.getString(6));
				list.add(s);
			}
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
