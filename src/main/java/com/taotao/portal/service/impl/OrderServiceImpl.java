package com.taotao.portal.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.po.TbUser;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	//#订单服务基础url
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	//#订单服务创建订单
	@Value("${ORDER_CRESTE_URL}")
	private String ORDER_CRESTE_URL;
	@Override
	public String createOrder(Order order,HttpServletRequest request) {
		
		//获得用户信息
		TbUser user=(TbUser)request.getAttribute("user");
		order.setUserId(user.getId());
		order.setBuyerNick(user.getUsername());
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CRESTE_URL,JsonUtils.objectToJson(order));
	    TaotaoResult result = TaotaoResult.format(json);
	  
	    		if(  result.getStatus()==200){
	    			Object orderId=result.getData();
	    			return orderId.toString();
	    		}
		System.out.println("订单服务请求失败");
		return "";
	}

	

}
