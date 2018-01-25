package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CatItem;

/**
 * 购物车Service
* <p>Title: CatService</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-18
 */
public interface CatService {

	/**
	 * 添加购物车
	 * <p>Title: addCartItem</p>  
	 * <p>Description: </p>  
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult addCartItem(long itemId,int num, 
			HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 从cookie中获得购物车信息
	 * <p>Title: getCartItemList</p>  
	 * <p>Description: </p>  
	 * @param request
	 * @return
	 */
	List<CatItem> getCartItemList(HttpServletRequest request);
	/**
	 * 根据商品id删除购物车中信息
	 * <p>Title: deleteCatItemByItemId</p>  
	 * <p>Description: </p>  
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult deleteCatItemByItemId(long itemId,
			HttpServletRequest request, HttpServletResponse response);
}
