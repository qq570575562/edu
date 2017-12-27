package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.Role;
import cn.wolfcode.edu.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Employee record);

	Employee selectByPrimaryKey(Long id);

	List<Employee> selectAll();

	int updateByPrimaryKey(Employee record);

	int editHead(Employee record);

	int queryForCount(QueryObject qo);

	List<Employee> queryForList(QueryObject qo);

	void changState(Long id);

	Employee getEmployeeByUsername(String username);

	List<Employee> selectByRoleSn(String sn);

	/**
	 * 建立关系
	 *
	 * @param roles
	 * @param employeeId
	 */
	void insertRelation(@Param("employeeId") Long employeeId, @Param("roles") List<Role> roles);
	/**
	 * 删除关系
	 *
	 * @param employeeId
	 */
	void deleteRelation(Long employeeId);

    /**
     * 根据员工id 获取所有角色
     * @param employeeId
     * @return
     */
    List<Long> selectByEmployeeId(Long employeeId);
}