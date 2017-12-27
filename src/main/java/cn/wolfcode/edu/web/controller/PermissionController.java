package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.domain.Permission;
import cn.wolfcode.edu.service.IPermissionService;
import cn.wolfcode.edu.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;

	@RequestMapping("selectAll")
	@ResponseBody
	public Object selectAll() throws Exception {
		List<Permission> permissions = permissionService.selectAll();
		return permissions;
	}

	/**
	 * 根据角色id 查询对应的权限
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selectByRoleId")
	@ResponseBody
	public Object selectByRoleId(Long roleId) throws Exception {
		List<Permission> permissions = permissionService.selectByRoleId(roleId);
		return permissions;
	}

	@RequestMapping("loadPermisson")
	@ResponseBody
	public JsonResult reloadPermisson() {
		try {
			permissionService.loadPermisson();
			return new JsonResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.getMessage());
		}
	}

}
