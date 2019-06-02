package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.alibaba.fastjson.JSONObject;
import com.entity.MSeat;
import com.entity.Seat;
import com.service.SeatManagerService;

@WebServlet("/SeatManagerController")
public class SeatManagerController extends HttpServlet {
	SeatManagerService ss = new SeatManagerService();

	public SeatManagerController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("first")) {
			List<MSeat> list = ss.queryAll();
			Map<String, List<MSeat>> map = new HashMap<>();
			map.put("Rows", list);
			String jsonOutput = JSON.toJSONString(map);

			out.print(jsonOutput);

		} else if (action.equals("edit")) {

			String sbin = request.getParameter("s");
			System.out.println(sbin);
			JSONObject jsonObject = JSONObject.parseObject(sbin);
			String SeatNumber = jsonObject.getString("Seat");
			String WhoName = jsonObject.getString("Operator");
			String Floor =jsonObject.getString("Floor");
			String data =jsonObject.getString("data");
			ss.edit(SeatNumber, WhoName,data,Floor);
			String jsonOutput = JSON.toJSONString("1");

			out.print(jsonOutput);
		} else if (action.equals("errseat")) {
			List<Seat> list = ss.queryAllerr();
			Map<String, List<Seat>> map = new HashMap<>();
			map.put("Rows", list);

			String jsonOutput = JSON.toJSONString(map);

			out.print(jsonOutput);

		}else if(action.equals("remove")){
			String Seat = request.getParameter("id");
			remove1(Seat);
			remove2(Seat);
			String jsonOutput = JSON.toJSONString("1");
			out.print(jsonOutput);
		}

	}

	private void remove2(String seat) {      //管理员释放报修座位
		JDBCUtil.sql = "delete from seaterr where seatNumber=?";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setString(1, seat);
			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void remove1(String seat) {      //用户释放座位
		JDBCUtil.sql = "UPDATE information set have=0,whoid=null,whoname=null where seatNumber=?;";
		JDBCUtil.ps = JDBCUtil.getPs();
		try {
			JDBCUtil.ps.setString(1, seat);

			JDBCUtil.ps.executeUpdate();
			JDBCUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}