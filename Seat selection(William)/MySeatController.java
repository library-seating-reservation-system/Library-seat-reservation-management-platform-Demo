package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Util.JDBCUtil;
import com.alibaba.fastjson.JSON;
import com.entity.MSeat;
import com.entity.Seat;
import com.entity.User;
import com.service.MySeatService;

@WebServlet("/MySeatController")
public class MySeatController extends HttpServlet {

	MySeatService ms = new MySeatService();

	public MySeatController() {              
		super();

	}           //²éÑ¯Ñ¡Ôñ×ùÎ»

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		User u = (User) request.getSession().getAttribute("uq");
		if (action.equals("first")) {
			List<Seat> list = ms.queryAll(u.getId());
			Map<String, List<Seat>> map = new HashMap<>();
			map.put("Rows", list);
			String jsonOutput = JSON.toJSONString(map);

			out.print(jsonOutput);

		} else if (action.equals("Reserve")) {              
			String seatNumber = request.getParameter("id");

			// if(flag(u.getId())){
			ms.delete(seatNumber);
			String jsonOutput = JSON.toJSONString("1");
			out.print(jsonOutput);
			// }else{
			// String jsonOutput= JSON.toJSONString("2");
			// out.print(jsonOutput);
			// }
			//
		}

	}

	// private boolean flag(Integer id) {
	// JDBCUtil.sql = "select count(*) from information where whoid=?";
	// JDBCUtil.ps = JDBCUtil.getPs();
	// int a = 0;
	// try {
	// JDBCUtil.ps.setString(1, id+"");
	// JDBCUtil.rs = JDBCUtil.ps.executeQuery();
	// if (JDBCUtil.rs.next()) {
	// a=JDBCUtil.rs.getInt(1);
	// }
	// JDBCUtil.close();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// if(a==0){
	// return true;
	// }else{
	// return false;
	// }
	//
	// }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}