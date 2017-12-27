package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.Department;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IDepartmentService {

	void deleteByPrimaryKey(Long id);

	void insert(Department record);

	Department selectByPrimaryKey(Long id);

	List<Department> selectAll();

	void updateByPrimaryKey(Department record);

	PageResult query(QueryObject qo);

    void changeState(Long id);
}
