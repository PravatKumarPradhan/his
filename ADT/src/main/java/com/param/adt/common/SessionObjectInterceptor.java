package com.param.adt.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
@SessionAttributes("sessionObject")
public class SessionObjectInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private SessionObject sessionObject; 
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try{
		if(sessionObject.getRoleMasterId() != null){
			/*if(request.getContentType() == null){
				Map<String,String> map = sessionObject.getAccessUrlMap();
				String[] url = request.getRequestURI().split("/");
				String page = map.get(url[url.length - 1]);
				if(page != null){
					return true;
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("/");
			        rd.forward(request, response);
					return false;
				}
			}*/
			return true;	
		}
		RequestDispatcher rd = request.getRequestDispatcher("/");
        rd.forward(request, response);
		return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}