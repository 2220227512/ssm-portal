package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.pojo.CatItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CatService;
import com.taotao.portal.service.OrderService;

/**
 * 订单controller
* <p>Title: OrderController</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-20
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CatService catService;
	
	@Autowired
	private OrderService orderService;
	@RequestMapping("/order-cart")
	public String toOrderPage(HttpServletRequest request,Model model){
		
		List<CatItem> cartList = catService.getCartItemList(request);
		model.addAttribute("cartList",cartList);
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String createOrder(Order order,Model model,HttpServletRequest request){
		try {
			String orderId = orderService.createOrder(order,request);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			model.addAttribute("message", "订单创建失败");
			return "error/exception";
		}
	

	}
}
