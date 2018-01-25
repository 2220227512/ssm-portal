package com.taotao.portal.service;

import com.taotao.po.TbUser;

/**
 * 用户Service接口
* <p>Title: UserService</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-18
 */
public interface UserService {
	/**
	 * 根据token获得用户信息
	 * <p>Title: getUserByToken</p>  
	 * <p>Description: </p>  
	 * @param token
	 * @return
	 */
	TbUser getUserByToken(String token);
	/**
	 * 获得首页登录地址
	 * <p>Title: toIndexUrl</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	String getToIndexUrl();

}
