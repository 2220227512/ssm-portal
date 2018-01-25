package com.taotao.portal.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.po.TbUser;
import com.taotao.portal.service.UserService;
/**
 * 登录拦截器
* <p>Title: LoginInterceptor</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-18
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handel) throws Exception {
		//获得cookin
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        //根据cookien查询用户信息
        TbUser user = userService.getUserByToken(token);
        //用户信息不存在
        if(user==null){
        	System.err.println(userService.getToIndexUrl());
        	response.sendRedirect(userService.getToIndexUrl()+"?redirect="+request.getRequestURL());
        	return false;
        }

        //用户信息
        request.setAttribute("user", user);
		return true;
	}

}
