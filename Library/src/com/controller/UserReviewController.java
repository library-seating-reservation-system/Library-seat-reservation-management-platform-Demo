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
import com.entity.UserAll;
import com.service.UserReviewService;

@WebServlet("/UserReviewController")
public class UserReviewController extends HttpServlet {
UserReviewService us = new UserReviewService();
	public UserReviewController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
          String action = request.getParameter("action");
//          System.out.println(action);
		    
		    
          if(action.equals("first")){ 
		    	   List<UserAll> list= us.queryAll();
		    	   Map<String,List<UserAll>> map = new HashMap<>();
		    	   map.put("Rows",list);
		    	   String jsonOutput= JSON.toJSONString(map); 
		    	   
		    		out.print(jsonOutput);
		    	   
		       }else if(action.equals("remove")){
		    	 String id = request.getParameter("id");
		    	 String uid="";
		 		JDBCUtil.sql = "SELECT id from  user where userName=?";
		 		JDBCUtil.ps = JDBCUtil.getPs();
		 		try {
		 			JDBCUtil.ps.setString(1, id);
		 			JDBCUtil.rs = JDBCUtil.ps.executeQuery();
		 			if (JDBCUtil.rs.next()) {
		 				uid=JDBCUtil.rs.getString(1);
						
					}
		 			JDBCUtil.close();
		 		} catch (Exception e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		 	        us.remove(uid);	
		 	       String jsonOutput= JSON.toJSONString("1"); 
		    	   
		    		out.print(jsonOutput);
             }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}