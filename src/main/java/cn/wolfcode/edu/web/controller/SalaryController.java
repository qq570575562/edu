package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.Salary;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.AttendanceQueryObject;
import cn.wolfcode.edu.service.IEmployeeService;
import cn.wolfcode.edu.service.ISalaryService;
import cn.wolfcode.edu.util.JsonResult;
import com.alibaba.druid.util.StringUtils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("salary")
public class SalaryController {
	@Autowired
	private ISalaryService salaryService;
	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("query")
	@RequiresPermissions("salary:query")
	@PerminssionName("薪资查询")
	@ResponseBody
	public PageResult list(AttendanceQueryObject qo) throws Exception {
		PageResult pageResult = salaryService.query(qo);
		return pageResult;
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public List<Salary> selectAll() {
		return salaryService.selectAll();
	}

	@RequestMapping("view")
	@RequiresPermissions("salary:view")
	@PerminssionName("薪资管理")
	public String view() {
		return "salary";
	}

	@RequestMapping("delete")
	@RequiresPermissions("salary:delete")
	@PerminssionName("薪资删除")
	@ResponseBody
	public JsonResult delete(Long id) {
		try {
			if (id != null) {
				salaryService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "删除失败");
		}
		return new JsonResult(true);
	}

	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("salary:saveOrUpdate")
	@PerminssionName("薪资更新")
	@ResponseBody
	public JsonResult saveOrUpdate(Salary salary) throws Exception {

		try {
			if (salary.getId() == null) {
				// 插入数据
				salaryService.insert(salary);

			} else {
				salaryService.updateByPrimaryKey(salary);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true);
	}
	@RequestMapping("updateSalary")
	@RequiresPermissions("salary:updateSalary")
	@PerminssionName("底薪更新")
	@ResponseBody
	public JsonResult updateSalary(Salary salary) throws Exception {

		try {
			salaryService.updateSalary(salary);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true);
	}
	@RequestMapping("updateForRow")
	@ResponseBody
	public JsonResult updateForRow(Salary salary) throws Exception {

		try {
			salaryService.updateForRow(salary);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "更新失败！");
		}
		return new JsonResult(true);
	}
	@RequestMapping("reloadAll")
	@RequiresPermissions("salary:reloadAll")
	@PerminssionName("薪资加载")
	@ResponseBody
	public JsonResult reloadAll() throws Exception {

		try {
			salaryService.reloadAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "更新失败！");
		}
		return new JsonResult(true);
	}
	@RequestMapping("adjust")
	@RequiresPermissions("salary:adjust")
	@PerminssionName("薪资核算")
	@ResponseBody
	public JsonResult adjustSalary(Salary salary) throws Exception {

		try {
			salaryService.adjustSalary(salary);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "计算薪资失败！");
		}
		return new JsonResult(true);
	}
	@RequestMapping("payOff")
	@RequiresPermissions("salary:payOff")
	@PerminssionName("薪资结算")
	@ResponseBody
	public JsonResult payOff(Salary salary) throws Exception {

		try {
			salaryService.payOff(salary);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

	@RequestMapping("importXls")
	@RequiresPermissions("salary:importXls")
	@PerminssionName("薪资导入")
	@ResponseBody
	public JsonResult importXls(MultipartFile file) throws Exception {

		// 获取xls文件
		Workbook workbook = Workbook.getWorkbook(file.getInputStream());
		try {
			// 获取工作簿
			Sheet sheet = workbook.getSheet(0);

			// 获取总行数
			int rows = sheet.getRows();

			// 读取数据并插入
			for (int i = 1; i < rows; i++) {

				Salary salary = new Salary();
				String employeeId = sheet.getCell(0, i).getContents();
				if (!StringUtils.isEmpty(employeeId)) {
					Employee employee = employeeService.selectByPrimaryKey(Long.parseLong(employeeId));
					salary.setEmployee(employee);
				}

				// 员工姓名
				if (!StringUtils.isEmpty(sheet.getCell(2, i).getContents())) {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
					// 设置月份
					salary.setMonth(sdf1.parse(sheet.getCell(2, i).getContents()));
				}

				// 设置基本薪资
				if (!StringUtils.isEmpty(sheet.getCell(3, i).getContents())) {
					salary.setSalary(new BigDecimal(sheet.getCell(3, i).getContents()));
				}

				// 设置工作日
				if (!StringUtils.isEmpty(sheet.getCell(4, i).getContents())) {
					salary.setWorkDay(Integer.parseInt(sheet.getCell(4, i).getContents()));
				}

				// 设置迟到次数
				if (!StringUtils.isEmpty(sheet.getCell(5, i).getContents())) {
					salary.setAfterNumber(Integer.parseInt(sheet.getCell(5, i).getContents()));
				}
				// 设置早退次数
				if (!StringUtils.isEmpty(sheet.getCell(6, i).getContents())) {
					salary.setBeforeNumber(Integer.parseInt(sheet.getCell(6, i).getContents()));
				}

				// 设置补签次数
				if (!StringUtils.isEmpty(sheet.getCell(7, i).getContents())) {
					salary.setResignNumber(Integer.parseInt(sheet.getCell(7, i).getContents()));
				}

				// 设置结算薪水
				if (!StringUtils.isEmpty(sheet.getCell(8, i).getContents())) {
					salary.setNowSalary(new BigDecimal(sheet.getCell(8, i).getContents()));
				}

				// 设置结算时间

				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 设置月份
				if (!StringUtils.isEmpty(sheet.getCell(9, i).getContents())) {
					salary.setMonth(sdf2.parse(sheet.getCell(9, i).getContents()));
				}

				// 保存
				salaryService.insert(salary);
			}
			return new JsonResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.getMessage());
		} finally {
			workbook.close();
		}
	}

	/**
	 * 导出表格
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXls")
	@RequiresPermissions("salary:exportXls")
	@PerminssionName("薪资导出")
	@ResponseBody
	public JsonResult exportXls(HttpServletResponse response) throws Exception {
		// 这是文件下载的响应头
		response.setHeader("Content-Disposition", "attachment;filename=salary.xls");

		// 创建一个文件
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

		// 创建工作簿
		WritableSheet sheet = workbook.createSheet("salary", 0);

		// 创建标题行

		sheet.addCell(new Label(0, 0, "员工工号"));
		sheet.addCell(new Label(1, 0, "员工姓名"));
		sheet.addCell(new Label(2, 0, "发放月份"));
		sheet.addCell(new Label(3, 0, "基本薪资"));
		sheet.addCell(new Label(4, 0, "工作天数"));
		sheet.addCell(new Label(5, 0, "迟到次数"));
		sheet.addCell(new Label(6, 0, "早退次数"));
		sheet.addCell(new Label(7, 0, "补签次数"));
		sheet.addCell(new Label(8, 0, "最终薪资"));
		sheet.addCell(new Label(9, 0, "发放日期"));

		// 获取数据库中的所有信息
		try {
			List<Salary> salaries = salaryService.selectAll();
			for (int i = 0, j = 1; i < salaries.size(); i++, j++) {
				Salary salary = salaries.get(i);
				if (salary.getEmployee().getId() != null) {
					sheet.addCell(new Label(0, j, salary.getEmployee().getId().toString()));
				}
				if (salary.getEmployee().getRealname() != null) {
					sheet.addCell(new Label(1, j, salary.getEmployee().getRealname().toString()));
				}
				if (salary.getMonth() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					sheet.addCell(new Label(2, j, salary.getMonth().toString()));
				}
				if (salary.getSalary() != null) {
					sheet.addCell(new Label(3, j, salary.getSalary().toString()));
				}
				if (salary.getWorkDay() != null) {
					sheet.addCell(new Label(4, j, salary.getWorkDay().toString()));
				}
				if (salary.getAfterNumber() != null) {
					sheet.addCell(new Label(5, j, salary.getAfterNumber().toString()));
				}
				if (salary.getBeforeNumber() != null) {
					sheet.addCell(new Label(6, j, salary.getBeforeNumber().toString()));
				}
				if (salary.getResignNumber() != null) {
					sheet.addCell(new Label(7, j, salary.getResignNumber().toString()));
				}
				if (salary.getNowSalary() != null) {
					sheet.addCell(new Label(8, j, salary.getNowSalary().toString()));
				}
				if (salary.getPaytime() != null) {
					sheet.addCell(new Label(9, j, salary.getPaytime().toLocaleString()));
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
	@RequestMapping("download")
	@ResponseBody
	public JsonResult ModelDownload(HttpServletResponse response) {
		try {
			// 这是文件下载的响应头
			response.setHeader("Content-Disposition", "attachment;filename=salary.xls");

			// 创建一个文件
			WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

			// 创建工作簿
			WritableSheet sheet = workbook.createSheet("salary", 0);

			// 创建标题行

			sheet.addCell(new Label(0, 0, "员工工号"));
			sheet.addCell(new Label(1, 0, "员工姓名"));
			sheet.addCell(new Label(2, 0, "发放月份"));
			sheet.addCell(new Label(3, 0, "基本薪资"));
			sheet.addCell(new Label(4, 0, "工作天数"));
			sheet.addCell(new Label(5, 0, "迟到次数"));
			sheet.addCell(new Label(6, 0, "早退次数"));
			sheet.addCell(new Label(7, 0, "补签次数"));
			sheet.addCell(new Label(8, 0, "最终薪资"));
			sheet.addCell(new Label(9, 0, "发放日期"));

			workbook.write();

			workbook.close();

			return new JsonResult(true);
		} catch (Exception e) {
			throw new RuntimeException("下载失败,请联系管理员");
		}

	}
}
