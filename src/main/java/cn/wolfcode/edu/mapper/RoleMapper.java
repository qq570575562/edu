package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Permission;
import cn.wolfcode.edu.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Role record);

	Role selectByPrimaryKey(Long id);

	List<Role> selectAll();

	int updateByPrimaryKey(Role record);

	void insertRelation(@Param("roleId") Long roleId, @Param("permissions") List<Permission> permissions);

	void deleteRelation(Long roleId);

	List<String> getRoleByEmployeeId(Long id);

}