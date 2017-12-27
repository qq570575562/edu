package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.PotentialStudent;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.PotentialStudentQueryObject;
import cn.wolfcode.edu.service.IPotentialStudentService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("potentialstudent")
public class PotentialStudentController {
	@Autowired
	private IPotentialStudentService potentialStudentService;
	@RequestMapping("view")
	@RequiresPermissions("potentialstudent:view")
	@PerminssionName("潜在学员查询")
	public String view() {
		return "potentialStudent";
	}

	@RequestMapping("query")
	@ResponseBody
	public PageResult list(PotentialStudentQueryObject qo) throws Exception {
		PageResult pageResult = null;
		pageResult = potentialStudentService.query(qo);
		return pageResult;
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public List<PotentialStudent> selectAll() {
		return potentialStudentService.selectAll();
	}
	@RequestMapping("checkstu")
	@ResponseBody
	@RequiresPermissions("potentialstudent:checkstu")
	@PerminssionName("查询所有非正式学员")
	public List<PotentialStudent> checkstu() {
		return potentialStudentService.checkstu();
	}
	@RequestMapping("delete")
	@ResponseBody
	@RequiresPermissions("potentialstudent:delete")
	@PerminssionName("放入资源池")
	public JsonResult delete(Long id) {
		try {
			potentialStudentService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			return new JsonResult(false, "放入资源池失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	@RequiresPermissions("potentialstudent:saveOrUpdate")
	@PerminssionName("添加和编辑")
	public JsonResult saveOrUpdate(PotentialStudent potentialStudent) {
		try {
			if (potentialStudent.getId() == null) {
				potentialStudentService.insert(potentialStudent);
			} else {
				potentialStudentService.updateByPrimaryKey(potentialStudent);
			}
		} catch (Exception e) {
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("changState")
	@ResponseBody
	@RequiresPermissions("potentialstudent:changState")
	@PerminssionName("转为正式员工")
	public JsonResult changState(Long id) {
		try {
			potentialStudentService.changState(id);
		} catch (Exception e) {
			return new JsonResult(false, "转正失败");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("changtial")
	@ResponseBody
	@RequiresPermissions("potentialstudent:changtial")
	@PerminssionName("跟踪记录")
	public JsonResult changtailnum(Long id) {
		try {
			potentialStudentService.tailnum(id);
		} catch (Exception e) {
			return new JsonResult(false, "跟踪失败");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("toPool")
	@ResponseBody
	@RequiresPermissions("potentialstudent:toPool")
	@PerminssionName("移入资源池")
	public JsonResult toPool(Long id) {
		try {
			potentialStudentService.toPool(id);
		} catch (Exception e) {
			return new JsonResult(false, "跟踪失败");
		}
		return new JsonResult(true, null);
	}

	

}