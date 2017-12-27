package cn.wolfcode.edu.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISystemLogService;
import cn.wolfcode.edu.util.JsonResult;

@Controller
@RequestMapping("systemLog")
public class SystemLogController {
	@Autowired
	private ISystemLogService systemLogService;

	@RequestMapping("view")
	@RequiresPermissions("systemLog:view")
	@PerminssionName("系统日志管理")
	public String view() {
		return "systemlog";
	}
	@RequestMapping("query")
	@ResponseBody
	public PageResult list(QueryObject qo) throws Exception {
		PageResult pageResult = systemLogService.query(qo);
		return pageResult;
	}

	@RequestMapping("deleteAll")
	@RequiresPermissions("systemLog:deleteAll")
	@PerminssionName("系统日志清除")
	@ResponseBody
	public JsonResult deleteAll() {
		try {

			systemLogService.deleteAll();

		} catch (Exception e) {
			return new JsonResult(false, "删除失败");
		}
		return new JsonResult(true);
	}

}
