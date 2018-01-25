package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CatItem;
import com.taotao.portal.service.CatService;

/**
 * 购物车Controller
* <p>Title: CartController</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-18
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CatService catService;
	
	@RequestMapping("/success")
	public String toCart(){
		return "cartSuccess";
	}
	
	@RequestMapping("/add/{itemId")
	public String addCart(HttpServletRequest request,HttpServletResponse response,
			@PathVariable Long itemId,@RequestParam(defaultValue="1") Integer num){
		TaotaoResult r = catService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/success.html";
	}
	
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request,Model model){
		List<CatItem> cartList = catService.getCartItemList(request);
		model.addAttribute("cartList", cartList);
		return "cart";
		
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		catService.deleteCatItemByItemId(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

	
}
