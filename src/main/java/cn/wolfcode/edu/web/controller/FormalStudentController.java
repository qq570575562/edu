package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.FormalStudent;
import cn.wolfcode.edu.domain.Runoff;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.FormalStudentQueryObject;
import cn.wolfcode.edu.service.IFormalStudentService;
import cn.wolfcode.edu.util.JsonResult;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("formalStudent")
public class FormalStudentController {
	@Autowired
	private IFormalStudentService formalStudentService;

	@RequiresPermissions("formalStudent:query")
	@PerminssionName("正式学员查询")
	@RequestMapping("query")
	@ResponseBody
	public PageResult list(FormalStudentQueryObject qo) throws Exception {
		PageResult pageResult = null;
		try {
			pageResult = formalStudentService.query(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResult;
	}
	@RequestMapping("selectAll")
	@ResponseBody
	public List<FormalStudent> selectAll() {
		return formalStudentService.selectAll();
	}

	@RequiresPermissions("formalStudent:view")
	@PerminssionName("正式学员菜单")
	@RequestMapping("view")
	public String view() {
		return "formalStudent";
	}

	@RequiresPermissions("formalStudent:saveOrUpdate")
	@PerminssionName("正式学员编辑")
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public JsonResult saveOrUpdate(FormalStudent formalStudent) throws Exception {
		try {
			if (formalStudent.getId() == null) {
				// 默认状态为正常
				formalStudent.setState(Byte.valueOf("1"));
				formalStudentService.insert(formalStudent);
			} else {
				formalStudentService.updateByPrimaryKey(formalStudent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true, "操作成功");
	}

	@RequestMapping("changeState")
	@ResponseBody
	public JsonResult changeState(Long id, Byte statVal) throws Exception {

		try {
			formalStudentService.changeState(id, statVal);
		} catch (Exception e) {
			return new JsonResult(false, "失败");
		}
		return new JsonResult(true, "成功");
	}

	@RequestMapping("selectOwnTuitionByid")
	@ResponseBody
	public BigDecimal selectOwnTuitionByid(Long id) throws Exception {

		return formalStudentService.selectOwnTuitionByid(id);

	}
	@RequiresPermissions("formalStudent:leavingStudent")
	@PerminssionName("正式学员编辑")
	@RequestMapping("leavingStudent")
	@ResponseBody
	public JsonResult leavingStudent(Long formalStuId, Runoff runoff) throws Exception {

		try {
			formalStudentService.leavingStudent(formalStuId, runoff);
		} catch (Exception e) {
			return new JsonResult(false, "失败");
		}
		return new JsonResult(true, "成功");

	}

	/**
	 * 导出表格
	 *
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXls")
	@RequiresPermissions("formalStudent:exportXls")
	@PerminssionName("大客户导出")
	@ResponseBody
	public JsonResult exportXls(HttpServletResponse response) throws Exception {

		// 这是文件下载的响应头
		response.setHeader("Content-Disposition", "attachment;filename=formalStudent.xls");

		// 创建一个文件
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

		// 创建工作簿
		WritableSheet sheet = workbook.createSheet("formalStudent", 0);

		// 创建标题行

		sheet.addCell(new Label(0, 0, "姓名"));
		sheet.addCell(new Label(1, 0, "销售人员"));
		sheet.addCell(new Label(2, 0, "总学费"));
		sheet.addCell(new Label(3, 0, "需缴学费"));
		sheet.addCell(new Label(4, 0, "已缴学费"));
		sheet.addCell(new Label(5, 0, "是否已付"));
		sheet.addCell(new Label(6, 0, "入学时间"));
		sheet.addCell(new Label(7, 0, "学校"));
		sheet.addCell(new Label(8, 0, "电话"));
		sheet.addCell(new Label(9, 0, "入学班级"));
		sheet.addCell(new Label(10, 0, "付款方式"));
		sheet.addCell(new Label(11, 0, "客户类型"));
		sheet.addCell(new Label(12, 0, "状态"));

		// 获取数据库中的所有信息

		try {
			List<FormalStudent> formalStudents = formalStudentService.selectAll();
			for (int i = 0, j = 1; i < formalStudents.size(); i++, j++) {
				FormalStudent formalStudent = formalStudents.get(i);
				if (formalStudent.getName() != null) {
					sheet.addCell(new Label(0, j, formalStudent.getName().toString()));
				}
				if (formalStudent.getSaleman() != null) {
					sheet.addCell(new Label(1, j, formalStudent.getSaleman().getRealname().toString()));
				}
				if (formalStudent.getTotalTuition() != null) {
					sheet.addCell(new Label(2, j, formalStudent.getTotalTuition().toString()));
				}
				if (formalStudent.getOwnTuition() != null) {
					sheet.addCell(new Label(3, j, formalStudent.getOwnTuition().toString()));
				}
				if (formalStudent.getPaidTuition() != null) {
					sheet.addCell(new Label(4, j, formalStudent.getPaidTuition().toString()));
				}

				if (formalStudent.isPaidState()) {
					sheet.addCell(new Label(5, j, formalStudent.isPaidState() + ""));
				}
				if (formalStudent.getEnrollDate() != null) {
					sheet.addCell(new Label(6, j, formalStudent.getEnrollDate().toString()));
				}
				if (formalStudent.getSchool() != null) {
					sheet.addCell(new Label(7, j, formalStudent.getSchool().toString()));
				}
				if (formalStudent.getTel() != null) {
					sheet.addCell(new Label(8, j, formalStudent.getTel().toString()));
				}
				if (formalStudent.getClz() != null) {
					sheet.addCell(new Label(9, j, formalStudent.getClz().getClassName().toString()));
				}
				if (formalStudent.getPayMode() != null) {
					sheet.addCell(new Label(10, j, formalStudent.getPayMode().toString()));
				}
				if (formalStudent.getClientType() != null) {
					sheet.addCell(new Label(11, j, formalStudent.getClientType().toString()));
				}
				if (formalStudent.getState() != null) {
					sheet.addCell(new Label(12, j, formalStudent.getState().toString()));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "导出出错,请检查数据是否正常");
		}

		workbook.write();

		workbook.close();

		return new JsonResult(true);
	}
}
