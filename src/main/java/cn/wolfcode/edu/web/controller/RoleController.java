package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Role;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IRoleService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private IRoleService roleService;

	@RequestMapping("view")
	public String index() {

		return "role";
	}

	@RequestMapping("selectAll")
	@RequiresPermissions("role:selectAll")
	@PerminssionName("角色查询")
	@ResponseBody
	public Object selectAll(QueryObject queryObject) {

		List<Role> roles = roleService.selectAll();
		return roles;
	}

	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("role:saveOrUpdate")
	@PerminssionName("角色更新")
	@ResponseBody
	public JsonResult update(Role entity) {
		if (entity.getId() != null) {
			roleService.updateByPrimaryKey(entity);
		} else {
			roleService.insert(entity);
		}
		return new JsonResult(true);
	}

	@RequestMapping("delete")
	@RequiresPermissions("role:delete")
	@PerminssionName("角色删除")
	@ResponseBody
	public JsonResult delete(Long id) {
		try {
			if (id != null) {
				roleService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "删除失败");
		}
		return new JsonResult(true);
	}

}
