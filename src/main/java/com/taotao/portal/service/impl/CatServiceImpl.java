package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.po.TbItem;
import com.taotao.portal.pojo.CatItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.CatService;

@Service
public class CatServiceImpl implements CatService{

	//#基础rest服务url片段
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	//#商品详情url
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	@Override
	public TaotaoResult addCartItem(long itemId, int num,
			HttpServletRequest request, HttpServletResponse response) {
		//获得cookie中购物车
		CatItem cartItem=null;
		List<CatItem> catList = getCartItemList(request);
		for (CatItem cat : catList) {
			//判断是否存在当前商品相关的信息
			if(cat.getId()==itemId){
				//存在更新数量
				cat.setNum(cat.getNum()+num);
				cartItem=cat;
				break;
			}
		}
		
		//cookie中不存在
		if(cartItem==null){
			//通过服务查找
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
			if(result.getStatus()==200){
				TbItem item=(TbItem)result.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());

			}
			//加入到
			catList.add(cartItem);
		}
		//存入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(catList), true);
		
		return null;
	}
	
	public List<CatItem> getCartItemList(HttpServletRequest request){
		String json = CookieUtils.getCookieValue(request, "TT_CART", true);
		if(json!=null){
			List<CatItem> lists = JsonUtils.jsonToList(json, CatItem.class);
			return lists;
		}else{
			return new ArrayList<>();
		}
	}

	@Override
	public TaotaoResult deleteCatItemByItemId(long itemId,
			HttpServletRequest request, HttpServletResponse response) {
		List<CatItem> cartList = getCartItemList(request);
		for (CatItem catItem : cartList) {
			if(catItem.getId()==itemId){
				cartList.remove(catItem);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList), true);
		
		return TaotaoResult.ok();
	}

}
