package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Attendance;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.AttendanceQueryObject;
import cn.wolfcode.edu.service.IAttendanceService;
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
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("attendance")
public class AttendanceController {
	@Autowired
	private IAttendanceService attendanceService;
	@RequestMapping("view")
	@RequiresPermissions("attendance:view")
	@PerminssionName("签到菜单")
	public String view() {
		return "attendance";
	}
	@RequestMapping("signIn")
	@ResponseBody
	public JsonResult signIn() {
		try {
			attendanceService.signIn();
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

	@RequestMapping("signOut")
	@ResponseBody
	public JsonResult signOut() {
		try {
			attendanceService.signOut();
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

	/**
	 * 补签操作
	 * 
	 * @param attendance
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("resignIn")
	@RequiresPermissions("attendance:resignIn")
	@PerminssionName("打卡补签")
	@ResponseBody
	public JsonResult resignIn(Attendance attendance) throws Exception {
		try {
			attendanceService.resignIn(attendance);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}
	@RequestMapping("query")
	@RequiresPermissions("attendance:query")
	@PerminssionName("签到查询")
	@ResponseBody
	public PageResult list(AttendanceQueryObject qo) throws Exception {
		PageResult pageResult = attendanceService.query(qo);
		System.out.println(pageResult);
		return pageResult;
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public List<Attendance> selectAll() {
		return attendanceService.selectAll();
	}
	@RequestMapping("exportXls")
	@RequiresPermissions("attendance:exportXls")
	@PerminssionName("打卡导出")
	@ResponseBody
	public JsonResult exportXls(HttpServletResponse response) throws Exception {
		// 这是文件下载的响应头
		response.setHeader("Content-Disposition", "attachment;filename=attendance.xls");
		// 创建一个文件
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
		// 创建工作簿
		WritableSheet sheet = workbook.createSheet("attendance", 0);
		// 创建标题行
		sheet.addCell(new Label(0, 0, "员工工号"));
		sheet.addCell(new Label(1, 0, "员工姓名"));
		sheet.addCell(new Label(2, 0, "有效日期"));
		sheet.addCell(new Label(3, 0, "签到IP"));
		sheet.addCell(new Label(4, 0, "签到时间"));
		sheet.addCell(new Label(5, 0, "签到状态"));
		sheet.addCell(new Label(6, 0, "签退时间"));
		sheet.addCell(new Label(7, 0, "签退状态"));
		sheet.addCell(new Label(8, 0, "补签时间"));
		sheet.addCell(new Label(9, 0, "补签人员"));

		// 获取数据库中的所有信息
		try {
			List<Attendance> attendances = attendanceService.selectAll();
			for (int i = 0, j = 1; i < attendances.size(); i++, j++) {
				Attendance attendance = attendances.get(i);
				if (attendance.getEmployee().getId() != null) {
					sheet.addCell(new Label(0, j, attendance.getEmployee().getId().toString()));
				}
				if (attendance.getEmployee().getRealname() != null) {
					sheet.addCell(new Label(1, j, attendance.getEmployee().getRealname().toString()));
				}
				if (attendance.getSignInDay() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					sheet.addCell(new Label(2, j, attendance.getSignInDay().toString()));
				}
				if (attendance.getSignInIp() != null) {
					sheet.addCell(new Label(3, j, attendance.getSignInIp().toString()));
				}
				if (attendance.getSignInTime() != null) {
					sheet.addCell(new Label(4, j, attendance.getSignInTime().toString()));
				}

				Boolean signInState = attendance.getSignInState();
				sheet.addCell(new Label(5, j, signInState == null ? "" : signInState ? "正常" : "迟到"));

				if (attendance.getSignOutTime() != null) {
					sheet.addCell(new Label(6, j, attendance.getSignOutTime().toString()));
				}
				Boolean signOutState = attendance.getSignOutState();
				sheet.addCell(new Label(7, j, signOutState == null ? "" : signOutState ? "正常" : "早退"));

				if (attendance.getResignInDate() != null) {
					sheet.addCell(new Label(8, j, attendance.getResignInDate().toLocaleString()));
				}
				if (attendance.getSupEmployee() != null) {
					sheet.addCell(new Label(9, j, attendance.getSupEmployee().toString()));
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
