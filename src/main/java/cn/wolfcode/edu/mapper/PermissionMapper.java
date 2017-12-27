package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Permission;

import java.util.List;

public interface PermissionMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Permission record);

	List<Permission> selectAll();
	
	List<String> selectAllResource();

	List<Permission> selectByRoleId(Long roleId);

	List<String> getResourceByEid(Long id);

}