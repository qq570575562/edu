package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.Classroom;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IClassroomService {

	void deleteByPrimaryKey(Long id);

	void insert(Classroom record);

	Classroom selectByPrimaryKey(Long id);

	List<Classroom> selectAll();

	void updateByPrimaryKey(Classroom record);

	PageResult query(QueryObject qo);

	void changeState(Long id);
}
