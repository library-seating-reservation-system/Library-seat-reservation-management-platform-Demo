package com.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//中文编码过滤器
@WebFilter("/*")
public class MyFilter implements Filter{

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
		
		}

		@Override  //核心处理方法
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
		
			//解决请求参数的中文乱码
			request.setCharacterEncoding("UTF-8");
	
			response.setContentType("text/html;charset=utf-8");
		
			//放行
			chain.doFilter(request, response);
			
		}
		
		@Override
		public void destroy() {
			//System.out.println("过滤器死亡");
		}


}