package com.pbc.pmtool.component;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.pbc.pmtool.entity.User;

@Component("defaultViewAttributeInterceptor")
public class DefaultViewAttributeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);		
		
		if(modelAndView != null) {
			if(!(modelAndView.getView() instanceof RedirectView || modelAndView.getViewName().startsWith("redirect:"))) {
				if(request.getMethod().equals("GET")) {
					try {
						modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
					}catch(Exception e){
					}
				}
			}
		}
	}
	
}
