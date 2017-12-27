package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.Role;
import cn.wolfcode.edu.mapper.EmployeeMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IEmployeeService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Employee record) {

		String username = record.getUsername();

		String password = record.getPassword();

		String newpassword = new Md5Hash(password, username, 2).toString();

		record.setPassword(newpassword);

		record.setState(true);
		employeeMapper.insert(record);
	}
	@Override
	public Employee selectByPrimaryKey(Long id) {
		return employeeMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Employee> selectAll() {
		return employeeMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Employee record) {
		List<Role> roles = record.getRoles();
		employeeMapper.deleteRelation(record.getId());
		if (roles.size() > 0) {
			employeeMapper.insertRelation(record.getId(), roles);
		}
		employeeMapper.updateByPrimaryKey(record);

	}
	@Override
	public void editHead(Employee record) {
		employeeMapper.editHead(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = employeeMapper.queryForCount(qo);
		if (total == 0) {
			return new PageResult(0, Collections.emptyList());
		}
		List<Employee> rows = employeeMapper.queryForList(qo);
		return new PageResult(total, rows);
	}

	@Override
	public void changState(Long id) {
		employeeMapper.changState(id);
	}

	// 通过用户名查询员工
	@Override
	public Employee getEmployeeByUsername(String username) {
		return employeeMapper.getEmployeeByUsername(username);
	}

	@Override
	public List<Employee> selectByRoleSn(String sn) {

		return employeeMapper.selectByRoleSn(sn);
	}

	@Override
	public List<Long> selectByEmployeeId(Long employeeId) {

		return employeeMapper.selectByEmployeeId(employeeId);

	}

}
