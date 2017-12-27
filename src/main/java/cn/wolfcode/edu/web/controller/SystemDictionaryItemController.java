package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.domain.SystemDictionaryItem;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISystemDictionaryItemService;
import cn.wolfcode.edu.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemDictionaryItem")
public class SystemDictionaryItemController {
	@Autowired
	private ISystemDictionaryItemService systemDictionaryItemService;
	
	
	@RequestMapping("query")
	@ResponseBody
	public PageResult query(QueryObject qo)throws Exception{
		PageResult pageResult = null;
		try {
			pageResult = systemDictionaryItemService.query(qo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return pageResult;
	}
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public JsonResult saveOrUpdate(SystemDictionaryItem systemDictionaryItem){
		try {
			if (systemDictionaryItem.getId()==null){
				systemDictionaryItemService.insert(systemDictionaryItem);
			}else{
				systemDictionaryItemService.updateByPrimaryKey(systemDictionaryItem);
			}
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(false,"保存失败！");
		}
		return new JsonResult(true,null);
	}
	@RequestMapping("queryBySn")
	@ResponseBody
	public List<SystemDictionaryItem> queryBySn(String sn) throws Exception{
		List<SystemDictionaryItem> items = systemDictionaryItemService.queryBySn(sn);
		System.out.println(items);
		return items;
	}
}
