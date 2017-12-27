package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.DesignateHistory;
import cn.wolfcode.edu.domain.PotentialStudent;
import cn.wolfcode.edu.domain.StudentPool;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.StudentPoolQueryObject;

import java.util.List;

public interface IStudentPoolService {
	void deleteByPrimaryKey(Long id);

	void insert(PotentialStudent record);

	StudentPool selectByPrimaryKey(Long id);

	List<StudentPool> selectAll();

	void updateByPrimaryKey(StudentPool record);

	PageResult query(StudentPoolQueryObject qo);

	void changState(Long id);

	void redo(DesignateHistory designateHistory);
}
