package org.aooshi.j.numberserver.filters;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aooshi.j.numberserver.domain.User;
import org.aooshi.j.util.Base64Helper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class BasicAuthorizationFilter implements Filter {
	
	private static HashMap<Integer,BasicAuthorizationUser> USER_MAP = new HashMap<Integer,BasicAuthorizationUser>();
 
	@Override
	public void destroy() {
		
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {



		
		ServletContext servletContext = request.getServletContext();
		boolean checkSuccess = check(request,servletContext);
		if (checkSuccess == true)
		{
			chain.doFilter(request, response);
		}
		else  if (((HttpServletRequest) request).getRequestURI().equals("/"))
		{
			chain.doFilter(request, response);
		}
		else
		{
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setCharacterEncoding("UTF-8");  
			//httpResponse.setContentType("application/json; charset=utf-8");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 			//
			httpResponse.getWriter().write("Unauthorized");
		}
	}
 
	@Override
	public void init(FilterConfig arg0) throws ServletException {		
		/*
		BasicAuthorizationUser testUser = new BasicAuthorizationUser();
		testUser.setExpire( Long.MAX_VALUE );
		testUser.setPwd("test");
		testUser.setUid(0);
		//
		USER_MAP.put(0, testUser);
		*/		
	}
	
	private boolean check(ServletRequest request,ServletContext servletContext)
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String auth = httpRequest.getHeader("Authorization");
		if ((auth != null) && (auth.length() > 6)) {
			String authorizeInfo = auth.substring(0, 5).toLowerCase();
			if (authorizeInfo.compareTo("basic") == 0) {
				auth = auth.substring(6, auth.length());
				String decodedAuth = Base64Helper.Decode(auth);
				if (decodedAuth != null) {
					String[] userArray = decodedAuth.split(":");

					if (userArray != null && userArray.length == 2) {
						
						return checkUser(userArray[0],userArray[1], servletContext);
					}
				}
			}
		}
		return false;	
	}
	
	private boolean checkUser(String uid,String pwd,ServletContext servletContext)
	{
		if (isNumeric(uid) == false)
			return false;
		
		int uidnum = Integer.parseInt(uid);
		//
		BasicAuthorizationUser basicAuthorizationUser = null;
		long millisecond = System.currentTimeMillis();
		//get
		synchronized (USER_MAP)
		{
			basicAuthorizationUser = USER_MAP.get(uidnum);
		}
		//check and find
		if (basicAuthorizationUser == null || millisecond > basicAuthorizationUser.getExpire())
		{
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			org.aooshi.j.numberserver.dao.UserImpl userImpl = webApplicationContext.getBean(org.aooshi.j.numberserver.dao.UserImpl.class);
			//
			User user = userImpl.findUserByUid(uidnum);
			if (user != null)
			{
				basicAuthorizationUser = new BasicAuthorizationUser();
				//allow cache 300 seconds
				basicAuthorizationUser.setExpire( millisecond + 300 * 1000 );
				basicAuthorizationUser.setPwd(user.getPwd());
				basicAuthorizationUser.setUid(uidnum);
				//
				synchronized (USER_MAP)
				{
					USER_MAP.put(uidnum, basicAuthorizationUser);
				}
			}
			else
			{
				basicAuthorizationUser = null;
			}
		}
		//
		if (basicAuthorizationUser == null)
			return false;
		//
		return basicAuthorizationUser.getPwd().equals(pwd);
	}
	
    private boolean isNumeric(String str){
    	if (str == null) return false;
        for (int i = str.length();--i>=0;){    
         if (!Character.isDigit(str.charAt(i))){  
          return false;  
         }  
        }  
        return true;  
    }
}
