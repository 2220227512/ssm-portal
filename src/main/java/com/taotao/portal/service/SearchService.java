package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * 调用solr服务接口
* <p>Title: SearchService</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-15
 */
public interface SearchService {
/**
 * 根据页面搜索获得查询条件和页数获得返回对象SearchResult
 * <p>Title: search</p>  
 * <p>Description: </p>  
 * @param queryString
 * @param page
 * @return
 */
	 SearchResult search(String queryString, int page) ;
}
