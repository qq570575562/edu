package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.DesignateHistory;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.StudentPoolQueryObject;
import cn.wolfcode.edu.service.IStudentPoolService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("studentPool")
public class StudentPoolController {
	@Autowired
	private IStudentPoolService studentPoolService;
	@RequestMapping("view")
	@RequiresPermissions("studentPool:view")
	@PerminssionName("资源池视图")
	public String view(){
		return "StudentPool";
	}
	@RequestMapping("query")
	@ResponseBody
	public PageResult list(StudentPoolQueryObject qo)throws Exception{
		PageResult pageResult = null;
		try {
			pageResult = studentPoolService.query(qo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return pageResult;
	}
	@RequestMapping("redo")
	@ResponseBody
	@RequiresPermissions("studentPool:redo")
	@PerminssionName("资源池指派")
	public JsonResult redo(DesignateHistory designateHistory){
		JsonResult jsonResult = null;
		try {
			studentPoolService.redo(designateHistory);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(false,"移交失败");
		}
		return jsonResult;
	}
}