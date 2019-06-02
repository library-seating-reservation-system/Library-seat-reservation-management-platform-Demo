package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.entity.User;
import com.service.UserService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
UserService us = new UserService();
	public LoginController() {          //µÇÂ½×¢²á
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if(action.equals("login")){
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			String level = request.getParameter("level");
			if(us.userLogin(userName, passWord,level ,request)){
				User u= (User) request.getAttribute("uq");
					out.print(true);
					
				
			}else{
				out.print(false);
			}
		}else if(action.equals("adduser")){
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			String name = request.getParameter("name");
			String grade = request.getParameter("grade");
			String department = request.getParameter("department");
			String profession = request.getParameter("profession");
			us.adduser(userName,passWord,name,department,grade,profession);
			  String jsonOutput= JSON.toJSONString("1"); 
	    		out.print(jsonOutput);
		}
		  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}