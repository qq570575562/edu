package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.Role;

import java.util.List;

public interface IRoleService {

	void insert(Role record);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKey(Role record);

	Role selectByPrimaryKey(Long id);

	List<Role> selectAll();
	List<String> getRoleByEmployeeId(Long id);

}
