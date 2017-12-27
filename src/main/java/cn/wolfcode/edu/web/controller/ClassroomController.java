package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Classroom;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IClassroomService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("classroom")
public class ClassroomController {
	@Autowired
	private IClassroomService classroomService;

	@RequestMapping("query")
	@RequiresPermissions("classroom:query")
	@PerminssionName("教室查询")
	@ResponseBody
	public PageResult list(QueryObject qo) throws Exception {
		PageResult pageResult = classroomService.query(qo);
		List<Classroom> rows = (List<Classroom>) pageResult.getRows();
		for (Classroom row : rows) {
			System.out.println(row);
		}
		return pageResult;
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public List<Classroom> selectAll() {
		return classroomService.selectAll();
	}

	@RequestMapping("view")
	@RequiresPermissions("classroom:view")
	@PerminssionName("教室管理")
	public String view() {
		return "classroom";
	}
	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("classroom:saveOrUpdate")
	@PerminssionName("教室更新")
	@ResponseBody
	public JsonResult saveOrUpdate(Classroom classroom) {
		try {
			if (classroom.getId() == null) {
				classroomService.insert(classroom);
			} else {
				classroomService.updateByPrimaryKey(classroom);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("changeState")
	@RequiresPermissions("classroom:changeState")
	@PerminssionName("教室状态设置")
	@ResponseBody
	public JsonResult changeState(Long id) throws Exception {
		try {
			if (id != null) {
				// 默认状态为正常
				classroomService.changeState(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "操作失败！");
		}
		return new JsonResult(true, null);
	}

}
