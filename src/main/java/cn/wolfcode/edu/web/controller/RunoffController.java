package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Runoff;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IRunoffService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("runoff")
public class RunoffController {
	@Autowired
	private IRunoffService service;
	@RequestMapping("view")
	@RequiresPermissions("runoff:view")
	@PerminssionName("流失视图")
	public String view() {
		return "runoff";
	}

	@RequestMapping("query")
	@ResponseBody
	public PageResult list(QueryObject qo) throws Exception {
		PageResult pageResult = service.query(qo);
		return pageResult;
	}
	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("runoff:saveOrUpdate")
	@PerminssionName("流失编辑")
	@ResponseBody
	public JsonResult saveOrUpdate(Runoff runoff){
		try {
			if (runoff.getId()!=null){
				service.updateByPrimaryKey(runoff);
			}else {
				service.insert(runoff);
			}
		}catch (Exception e){
			return new JsonResult(false,"操作失败");
		}
		return new JsonResult(true,null);
	}
}
