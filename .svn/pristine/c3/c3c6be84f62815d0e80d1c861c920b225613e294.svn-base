package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索功能Controller
* <p>Title: SearchController</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-15
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	/**
	 * 搜索功能
	 * 接收页面搜索条件分页
	 * <p>Title: search</p>  
	 * <p>Description: </p>  
	 * @param queryString
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String search(@RequestParam(value="q")String queryString ,
			@RequestParam(defaultValue="1")Integer page,Model model){
		
		if(queryString!=null){
			try {
				queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
		}
		SearchResult searchResult = searchService.search(queryString, page);
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", page);
		
		return "search";

		
	}
}
