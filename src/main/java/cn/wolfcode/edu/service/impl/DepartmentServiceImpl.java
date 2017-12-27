package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Department;
import cn.wolfcode.edu.mapper.DepartmentMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	private DepartmentMapper departmentMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		departmentMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Department record) {
		departmentMapper.insert(record);
	}
	@Override
	public Department selectByPrimaryKey(Long id) {
		return departmentMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Department> selectAll() {
		return departmentMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Department record) {
		departmentMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = departmentMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<Department> rows = departmentMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	/**
	 * 修改状态
	 * @param id
	 */
	@Override
	public void changeState(Long id) {
		departmentMapper.changeState(id);
	}
}
