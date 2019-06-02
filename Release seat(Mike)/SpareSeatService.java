package com.service;

import java.util.ArrayList;
import java.util.List;

import com.Util.JDBCUtil;
import com.Util.NewTime;
import com.dao.SpareSeatDao;
import com.entity.Seat;

public class SpareSeatService {
SpareSeatDao ss = new SpareSeatDao();
	public List<Seat> queryAll() {
		
		 JDBCUtil.sql = "UPDATE information set time=? where have=0;";
			JDBCUtil.ps = JDBCUtil.getPs();
			try {
				JDBCUtil.ps.setString(1,new NewTime().LoginTime());
				JDBCUtil.ps.executeUpdate();
				JDBCUtil.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		List<Seat> list = ss.queryAll();
		return list;
	}
	public void add(Integer id, String id2) {
		// TODO Auto-generated method stub
		String name = "";
		JDBCUtil.sql = "select name from message where id=?";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setInt(1, id);
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			if(JDBCUtil.rs.next()) {
				name=JDBCUtil.rs.getString(1);
			}
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	    ss.add(id,id2,name);
	  
	}
	public void adderr(Integer id, String name, String seatnumber, String date) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println(id);
				String uname = "";
				JDBCUtil.sql = "select name from message where id=?";
				JDBCUtil.ps = JDBCUtil.getPs();
				try {
					JDBCUtil.ps.setInt(1, id);
					JDBCUtil.rs = JDBCUtil.ps.executeQuery();
					if(JDBCUtil.rs.next()) {
						uname=JDBCUtil.rs.getString(1);
					}
					JDBCUtil.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(uname);
				ss.adderr(id,uname,name,seatnumber,date);
	}

}
