package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IEmployeeService {

	void deleteByPrimaryKey(Long id);

	void insert(Employee record);

	Employee selectByPrimaryKey(Long id);

	List<Employee> selectAll();

	void updateByPrimaryKey(Employee record);
	void editHead(Employee record);

	PageResult query(QueryObject qo);

	void changState(Long id);

	Employee getEmployeeByUsername(String username);

	List<Employee> selectByRoleSn(String sn);

	List<Long> selectByEmployeeId(Long id);
}
