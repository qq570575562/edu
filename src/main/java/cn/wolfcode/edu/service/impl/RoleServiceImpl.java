package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Permission;
import cn.wolfcode.edu.domain.Role;
import cn.wolfcode.edu.mapper.RoleMapper;
import cn.wolfcode.edu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	RoleMapper roleMapper;

	@Override
	public void insert(Role record) {
		roleMapper.insert(record);
		List<Permission> permissions = record.getPermissions();
		System.out.println(permissions);
		// 建立关系
		if (permissions.size() != 0) {

			roleMapper.insertRelation(record.getId(), permissions);
		}

	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		roleMapper.deleteRelation(id);
		roleMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void updateByPrimaryKey(Role record) {
		// 删除关系
		roleMapper.deleteRelation(record.getId());

		List<Permission> permissions = record.getPermissions();
		// 建立关系
		if (permissions.size() != 0) {

			roleMapper.insertRelation(record.getId(), permissions);
		}

		roleMapper.updateByPrimaryKey(record);

	}

	@Override
	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}
	public List<String> getRoleByEmployeeId(Long id) {
		return roleMapper.getRoleByEmployeeId(id);
	}

}
