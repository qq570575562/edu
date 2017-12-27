package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.StudentTail;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.StudentTailQueryObject;
import cn.wolfcode.edu.service.IStudentTailService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("studenttail")
public class StudentTailController {
	@Autowired
	private IStudentTailService studentTailService;
	@RequestMapping("view")
	@RequiresPermissions("studenttail:view")
	@PerminssionName("学员信息跟踪管理")
	public String view(){
		return "studenttail";
	}
	
	
	@RequestMapping("query")
	@RequiresPermissions("studenttail:query")
	@PerminssionName("学员跟踪信息查询")
	@ResponseBody
	public PageResult list(StudentTailQueryObject qo)throws Exception{
		PageResult pageResult = null;
		try {
			pageResult = studentTailService.query(qo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return pageResult;
	}
	@RequestMapping("selectAll")
	@ResponseBody
	public List<StudentTail> selectAll() {
		return  studentTailService.selectAll();
	}
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	@RequiresPermissions("studenttail:saveOrUpdate")
	@PerminssionName("学员跟踪编辑")
	public JsonResult saveOrUpdate(StudentTail studentTail){
		try {
			if (studentTail.getId()==null){
				studentTailService.insert(studentTail);
			}else{
				studentTailService.updateByPrimaryKey(studentTail);
			}
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(false,"保存失败！");
		}
		return new JsonResult(true,null);
	}
	
	@RequestMapping("changState")
	@RequiresPermissions("studenttail:changState")
	@PerminssionName("学员审核状态更改")
	@ResponseBody
	public  JsonResult changState(StudentTail studentTail){
		try {
			studentTailService.changState(studentTail);
		}catch (Exception e){
			return new JsonResult(false,"审核失败");
		}
		return new JsonResult(true,null);
	}
}
