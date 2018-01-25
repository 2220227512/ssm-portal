package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	//调用sorl服务URL *注意配置是否错误
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryString, int page) {
		Map<String, String> param =new HashMap<String, String>();
		param.put("q", queryString);
		param.put("page", page+"");
		//param.put("rows", rows);
		
		
		try {
			//调用http服务获得json数据
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			//返回为search类型json
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
			//判断是否请求成功
			if(taotaoResult.getStatus()==200){
				SearchResult data = (SearchResult)taotaoResult.getData();
				return data;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

}
