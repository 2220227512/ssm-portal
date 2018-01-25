package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.po.TbUser;
import com.taotao.portal.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	//#单点登录系统基础url
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	//#单点登录系统 根据token获得用户信息
	@Value("${SSO_TOKEN_URL}")
	private String SSO_TOKEN_URL;
	//#单点登录系统登录页面url
	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;
	@Override
	public TbUser getUserByToken(String token) {
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL+SSO_TOKEN_URL+token);
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if(result.getStatus()==200){
				TbUser user =(TbUser)result.getData();
				return user;
			}
			
		} catch (Exception e) {
			System.out.println("当前地址出现异常 ：com.taotao.portal.service.impl.UserServiceImpl");
		}
		return null;
	}
	
	public String getToIndexUrl(){
		return SSO_BASE_URL+SSO_LOGIN_URL;
	}

}
