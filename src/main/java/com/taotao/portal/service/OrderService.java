package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;

import com.taotao.portal.pojo.Order;

/**
 * 订单service
* <p>Title: OrderService</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-20
 */
public interface OrderService {
/**
 * 创建订单
 * <p>Title: createOrder</p>  
 * <p>Description: </p>  
 * @param order
 * @return
 */
	String createOrder(Order order,HttpServletRequest request);
}
