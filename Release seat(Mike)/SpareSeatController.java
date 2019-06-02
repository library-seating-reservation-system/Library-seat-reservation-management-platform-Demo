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
import com.entity.Seat;
import com.entity.User;
import com.service.SpareSeatService;

@WebServlet("/SpareSeatController")
public class SpareSeatController extends HttpServlet {
      SpareSeatService ss = new SpareSeatService();
	public SpareSeatController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
              String action = request.getParameter("action");
		    
		    
              if(action.equals("first")){ 
		    	   List<Seat> list= ss.queryAll();
		    	   Map<String,List<Seat>> map = new HashMap<>();
		    	   map.put("Rows",list);
		    	   String jsonOutput= JSON.toJSONString(map); 
		    	   
		    		out.print(jsonOutput);
		    	   
		       }else if(action.equals("Reserve")){
		    	User u =(User) request.getSession().getAttribute("uq");
		    	 String id= request.getParameter("id");  
		    	
		    		
		    		 if(flag(u.getId())){
		    			 ss.add(u.getId(),id);
				    	 String jsonOutput= JSON.toJSONString("1"); 
				    	   
				    		out.print(jsonOutput);
			    	  }else{
			    		  String jsonOutput= JSON.toJSONString("2"); 
				    	   
				    		out.print(jsonOutput);
			    	  }
		    		
		    		
		       }else if(action.equals("adderr")){       //报修
		    	   String name = request.getParameter("name");
		    	   String Seatnumber = request.getParameter("Seatnumber");
		    	   String date = request.getParameter("date");
		    	   User u =(User) request.getSession().getAttribute("uq");
		    	   ss.adderr(u.getId(),name,Seatnumber,date);
		    	   String jsonOutput= JSON.toJSONString("1"); 
		    		out.print(jsonOutput);
		       }
		
		
	}
	private boolean flag(Integer id) {     //查询当前用户以前是否有占位记录
		JDBCUtil.sql = "select count(*) from information where whoid=?";
		JDBCUtil.ps = JDBCUtil.getPs();
		int a = 0;
		try {
			JDBCUtil.ps.setString(1, id+"");
			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
			if (JDBCUtil.rs.next()) {
				a=JDBCUtil.rs.getInt(1);
			}
			JDBCUtil.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(a==0){
			return true;
		}else{
			return false;
			}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}