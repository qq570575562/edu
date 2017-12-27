package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.SystemDictionary;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISystemDictionaryService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemDictionary")
public class SystemDictionaryController {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	@RequestMapping("query")
	@ResponseBody
	public PageResult query(QueryObject qo) throws Exception {
		PageResult pageResult = null;
		try {
			pageResult = systemDictionaryService.query(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResult;
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public List<SystemDictionary> selectAll() {
		return systemDictionaryService.selectAll();
	}

	@RequestMapping("view")
	@RequiresPermissions("systemDictionary:view")
	@PerminssionName("数据字典管理")
	public String view() {
		return "systemDictionary";
	}

	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("systemDictionary:saveOrUpdate")
	@PerminssionName("数据字典更新")
	@ResponseBody
	public JsonResult saveOrUpdate(SystemDictionary systemDictionary) {
		try {
			if (systemDictionary.getId() == null) {
				systemDictionaryService.insert(systemDictionary);
			} else {
				systemDictionaryService.updateByPrimaryKey(systemDictionary);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true, null);
	}

}
