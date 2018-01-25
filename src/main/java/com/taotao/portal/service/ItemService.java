package com.taotao.portal.service;

import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;

/**
 * 客户端商品详情Service
* <p>Title: ItemService</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-16
 */
public interface ItemService {

	/**
	 * 根据商品id调用服务端获得商品详情
	 * <p>Title: getItemById</p>  
	 * <p>Description: </p>  
	 * @param itemId
	 * @return
	 */
	ItemInfo getItemById(Long itemId) ;
	
	/**
	 * 根据商品id调用服务端获得商品描述详情
	 * <p>Title: getItemDestById</p>  
	 * <p>Description: </p>  
	 * @param itemId
	 * @return
	 */
	String getItemDestById(Long itemId);
	
	
	 /**
	  *  根据商品id调用服务端获得商品规格参数详情
	  * <p>Title: getItemParamById</p>  
	  * <p>Description: </p>  
	  * @param itemId
	  * @return
	  */
	String getItemParamById(Long itemId);
}
