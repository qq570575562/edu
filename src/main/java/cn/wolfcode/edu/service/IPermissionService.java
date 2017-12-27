package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.Permission;

import java.util.List;

public interface IPermissionService {

	void insert(Permission record);

	void deleteByPrimaryKey(Long id);

	List<Permission> selectAll();

	
	List<Permission> selectByRoleId(Long roleId);

	void loadPermisson();

	List<String> getResourceByEid(Long id);
}
