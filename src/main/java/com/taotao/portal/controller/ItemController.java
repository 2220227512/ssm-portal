package com.taotao.portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.po.TbItemDesc;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
/**
 * 客户端Item的Controller
* <p>Title: ItemController</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-16
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model){
		ItemInfo item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
		
	}
	
	@RequestMapping(value="/item/desc/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getitemDesc(@PathVariable Long itemId){
		 String itemDes = itemService.getItemDestById(itemId);
		return itemDes;
	}
	
	
	@RequestMapping(value="/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getitemParam(@PathVariable Long itemId){
		System.out.println("1111111111");
		 String itemDes = itemService.getItemParamById(itemId);
		 System.out.println(itemDes);
		return itemDes;
	}
}
