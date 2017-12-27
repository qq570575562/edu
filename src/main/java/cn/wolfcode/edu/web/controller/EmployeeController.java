package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IEmployeeService;
import cn.wolfcode.edu.util.JsonResult;
import cn.wolfcode.edu.util.UploadUtil;
import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private ServletContext ctx;

	@RequestMapping("query")
	@RequiresPermissions("employee:query")
	@PerminssionName("员工查询")
	@ResponseBody
	public PageResult list(QueryObject qo) throws Exception {
		PageResult pageResult = null;
		try {
			pageResult = employeeService.query(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResult;
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public List<Employee> selectAll() {
		return employeeService.selectAll();
	}

	@RequestMapping("view")
	@RequiresPermissions("employee:view")
	@PerminssionName("员工管理")
	public String view() {
		return "employee";
	}
	@RequestMapping("editHeadview")
	public String editHeadview() {
		return "headEdit";
	}

	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("employee:saveOrUpdate")
	@PerminssionName("员工更新")
	@ResponseBody
	public JsonResult saveOrUpdate(Employee employee) {
		try {
			if (employee.getId() == null) {
				employeeService.insert(employee);
			} else {
				employeeService.updateByPrimaryKey(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true);
	}
	@RequestMapping("changState")
	@RequiresPermissions("employee:changState")
	@PerminssionName("员工离复职")
	@ResponseBody
	public JsonResult changState(Long id) {
		try {
			employeeService.changState(id);
		} catch (Exception e) {
			return new JsonResult(false, "修改失败！");
		}
		return new JsonResult(true, null);
	}

	@RequestMapping("selectByRoleSn")
	@ResponseBody
	public List<Employee> selectByRoleSn(String sn) {
		List<Employee> employees = employeeService.selectByRoleSn(sn);
		return employees;
	}

	@RequestMapping("editHead")
	@ResponseBody
	public JsonResult editHead(Long id, MultipartFile picture) {
		Employee employee = employeeService.selectByPrimaryKey(id);

		// 判断用户是否上图片,数据库中是否有图片
		// 保存前删除数据库中的图片
		if (picture != null && !StringUtils.isEmpty(employee.getImagePath())) {
			UploadUtil.deleteFile(ctx, employee.getImagePath());
		}
		if (picture != null) {
			String imagePath = UploadUtil.upload(picture, ctx.getRealPath("/static/upload"));
			employee.setImagePath(imagePath);
		}
		employeeService.updateByPrimaryKey(employee);
		return new JsonResult(true);
	}

	// 获取员工的角色信息
	@RequestMapping("selectByEmployeeId")
	@ResponseBody
	public Object selectByEmployeeId(Long id) {

		List<Long> roleIds = employeeService.selectByEmployeeId(id);
		return roleIds;
	}

	// 注册

	@RequestMapping("register")
	@ResponseBody
	public JsonResult register(Employee employee) {
		String username = employee.getUsername();

		String password = employee.getPassword();

		String newpassword = new Md5Hash(password, username, 2).toString();

		employee.setPassword(newpassword);

		employeeService.insert(employee);

		return new JsonResult(true);
	}
}
