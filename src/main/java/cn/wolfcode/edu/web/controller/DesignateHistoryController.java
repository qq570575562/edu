package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.DesignateHistoryQueryObject;
import cn.wolfcode.edu.service.IDesignateHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("designatehistory")
public class DesignateHistoryController {
	@Autowired
	private IDesignateHistoryService designateHistoryService;

	@RequestMapping("view")
	@RequiresPermissions("designatehistory:view")
	@PerminssionName("移交历史视图")
	public String view(){
		return "designatehistory";
	}

	@RequestMapping("query")
	@ResponseBody
	public PageResult list(DesignateHistoryQueryObject qo)throws Exception{
		PageResult pageResult = null;
		try {
			pageResult = designateHistoryService.query(qo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return pageResult;
	}
	@RequestMapping("exportXls")
	@RequiresPermissions("designatehistory:exportXls")
	@PerminssionName("移交导出")
	public void exportXls(HttpServletResponse resp){
		try {
			designateHistoryService.exportXls(resp);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
