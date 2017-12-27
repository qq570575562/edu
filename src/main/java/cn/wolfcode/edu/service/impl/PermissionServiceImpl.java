package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Permission;
import cn.wolfcode.edu.mapper.PermissionMapper;
import cn.wolfcode.edu.service.IPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	PermissionMapper permissionMapper;
	@Autowired
	ApplicationContext context;

	@Override
	public void insert(Permission record) {
		permissionMapper.insert(record);

	}

	@Override
	public void deleteByPrimaryKey(Long id) {

		permissionMapper.deleteByPrimaryKey(id);

	}

	@Override
	public List<Permission> selectAll() {
		return permissionMapper.selectAll();
	}
	@Override
	public List<String> getResourceByEid(Long id) {
		return permissionMapper.getResourceByEid(id);
	}

	@Override
	public List<Permission> selectByRoleId(Long roleId) {
		return permissionMapper.selectByRoleId(roleId);
	}

	@Override
	public void loadPermisson() {
		// 获取数据库中已存在的所有权限
		List<String> resources = permissionMapper.selectAllResource();

		// 获取需要权限的类
		Map<String, Object> controllerMaps = context.getBeansWithAnnotation(Controller.class);
		// 获取全部的类名
		Collection<Object> controllers = controllerMaps.values();

		// 遍历所有的类
		for (Object controller : controllers) {
			// 获取所有的类中的方法
			Method[] methods = controller.getClass().getSuperclass().getDeclaredMethods();

			for (Method method : methods) {

				// 检查方法是否使用注解标记
				RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);

				if (annotation != null) {
					// 获取注解中的内容
					String[] value = annotation.value();
					// 查看是否已存在
					if (!resources.contains(value[0])) {
						// 不存在就存起来
						Permission permission = new Permission();
						permission.setResource(value[0]);
						// 获取权限的名称
						String permissionName = method.getAnnotation(PerminssionName.class).value();
						permission.setName(permissionName);
						permissionMapper.insert(permission);
					}
				}
			}
		}
	}
}
