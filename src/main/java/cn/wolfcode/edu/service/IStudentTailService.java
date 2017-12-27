package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.StudentTail;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.StudentTailQueryObject;

import java.util.List;


public interface IStudentTailService {
	void deleteByPrimaryKey(Long id);

	void insert(StudentTail record);

	StudentTail selectByPrimaryKey(Long id);

	List<StudentTail> selectAll();

	void updateByPrimaryKey(StudentTail record);

	PageResult query(StudentTailQueryObject qo);

	void changState(StudentTail studentTail);
}

