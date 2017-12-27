package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Examination;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.ExaminationQueryObject;
import cn.wolfcode.edu.service.IExaminationService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("examination")
public class ExaminationController {
	@Autowired
	private IExaminationService examinationService;

	@RequestMapping("query")
	@RequiresPermissions("examination:query")
	@PerminssionName("考试查询")
	@ResponseBody
	public PageResult list(ExaminationQueryObject qo) throws Exception {
		PageResult pageResult = null;
		try {
			pageResult = examinationService.query(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResult;
	}
	@RequestMapping("view")
	@RequiresPermissions("examination:view")
	@PerminssionName("考试管理")
	public String view() {
		return "examination";
	}
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	@RequiresPermissions("examination:saveOrUpdate")
	@PerminssionName("考试添加和编辑")
	public JsonResult saveOrUpdate(Examination examination) {
		try {
			if (examination.getId() == null) {
				List<Long> psids = examinationService.selectPsId();
				if (psids.contains(examination.getPsId())) {
					return new JsonResult(false, "该学生已经申请考试");
				}
				examinationService.insert(examination);
			} else {
				examinationService.updateByPrimaryKey(examination);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("changState")
	@RequiresPermissions("examination:changState")
	@PerminssionName("考试状态修改")
	@ResponseBody
	public JsonResult changState(Long id) {
		try {
			examinationService.changState(id);
		} catch (Exception e) {
			return new JsonResult(false, "修改失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("delete")
	@ResponseBody
	@RequiresPermissions("examination:delete")
	@PerminssionName("考试删除")
	public JsonResult delete(Long id) {
		try {
			examinationService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			return new JsonResult(false, "删除失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("exportXls")
	@RequiresPermissions("examination:exportXls")
	@PerminssionName("考试导出")
	public void exportXls(HttpServletResponse resp){
		try {
			examinationService.exportXls(resp);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
