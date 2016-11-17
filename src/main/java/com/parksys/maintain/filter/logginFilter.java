package com.parksys.maintain.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.parksys.maintain.base.BaseController;

public class logginFilter extends OncePerRequestFilter{

	public String LONGIN_URL="/login.do";
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean authorzed=validateFilter(request,response);
		if(authorzed){
			filterChain.doFilter(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+LONGIN_URL);
		}
	}
	/**
	 * 校驗用戶是否已經登錄
	 */
	public boolean validateFilter(HttpServletRequest request,HttpServletResponse response){
		
		/**
		 * @function 添加链接白名单
		 */
		String[] noFilter=new String[] {"",""};
		String uri=request.getRequestURI();
		for(String item:noFilter){
			if(uri.indexOf(item) !=-1){
				return true;
			}
		}
		// .html,.do之外的请求直接放行
				if (!StringUtils.endsWithAny(uri, ".htm", ".do")) {
					return true;
				}
		return false;
	}
	
}
