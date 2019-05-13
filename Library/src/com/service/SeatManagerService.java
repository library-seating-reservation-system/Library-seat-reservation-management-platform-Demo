package com.service;

import java.util.List;

import com.Util.JDBCUtil;
import com.dao.SeatManagerDao;
import com.entity.MSeat;
import com.entity.Seat;

public class SeatManagerService {
SeatManagerDao sm = new SeatManagerDao();
public List<MSeat> queryAll() {
	 List<MSeat> list = sm.queryAll();
	return list;
}
public void edit(String seatnumbner, String oper,String data,String Floor) {
	if(oper==""||oper==null){
		JDBCUtil.sql = "UPDATE information set have=0,whoid=null,whoname=null where seatNumber =?;";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setString(1,seatnumbner);
			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else{
		String id="";
		JDBCUtil.sql = "select * from message where name =? ";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setString(1,oper);
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			if(JDBCUtil.rs.next()) {
			id = JDBCUtil.rs.getString(1);
				System.out.println(id);
			}
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sm.edit(seatnumbner,oper,id,data,Floor);
	}
	
}
public List<Seat> queryAllerr() {
	List<Seat> list =sm.queryAllerr();

	return list;
	
}

}
