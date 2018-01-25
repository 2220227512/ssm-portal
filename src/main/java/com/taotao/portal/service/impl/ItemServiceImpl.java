package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	// 注入服务端基本url
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	// 注入商品详情url
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	// 注入商品描述url
	@Value("${ITEM_PAEAM_URL}")
	private String ITEM_PAEAM_URL;

	// 注入商品规格参数url

	@Override
	public ItemInfo getItemById(Long itemId) {

		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL
					+ itemId);
			if (!StringUtils.isBlank(json)) {
				TaotaoResult tatoaresult = TaotaoResult.formatToPojo(json,
						ItemInfo.class);
				if(tatoaresult.getStatus()==200){
					ItemInfo info=(ItemInfo)tatoaresult.getData();
					return info;
				}
				
			}

		} catch (Exception e) {
			//这是ItemServiceimpl的getItemById方法出错
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemDestById(Long itemId) {

		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL
					+ itemId);
			if (!StringUtils.isBlank(json)) {
				TaotaoResult tatoaresult = TaotaoResult.formatToPojo(json,
						TbItemDesc.class);
				if(tatoaresult.getStatus()==200){
					TbItemDesc desc=(TbItemDesc)tatoaresult.getData();
					String itemDesc = desc.getItemDesc();
					return itemDesc;
				}
			
			}

		} catch (Exception e) {
			//这是ItemServiceimpl的getItemDestById方法出错
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemParamById(Long itemId) {

		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PAEAM_URL
					+ itemId);
			if (!StringUtils.isBlank(json)) {
				TaotaoResult tatoaresult = TaotaoResult.formatToPojo(json,
						TbItemParamItem.class);
				if(tatoaresult.getStatus()==200){
					TbItemParamItem param=(TbItemParamItem)tatoaresult.getData();
					String paramData = param.getParamData();
					//生成html
					// 把规格参数json数据转换成java对象
					List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
					StringBuffer sb = new StringBuffer();
					sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
					sb.append("    <tbody>\n");
					for(Map m1:jsonList) {
						sb.append("        <tr>\n");
						sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
						sb.append("        </tr>\n");
						List<Map> list2 = (List<Map>) m1.get("params");
						for(Map m2:list2) {
							sb.append("        <tr>\n");
							sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
							sb.append("            <td>"+m2.get("v")+"</td>\n");
							sb.append("        </tr>\n");
						}
					}
					sb.append("    </tbody>\n");
					sb.append("</table>");
					//返回html片段
					return sb.toString();

				}
				
			}

		} catch (Exception e) {
			//这是ItemServiceimpl的getItemParamById方法出错
			e.printStackTrace();
		}
		return null;
	}

}
