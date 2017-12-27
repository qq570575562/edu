package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.Course;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.CourseQueryObject;

import java.util.List;

public interface ICourseService {

	void deleteByPrimaryKey(Long id);

	void insert(Course record);

	Course selectByPrimaryKey(Long id);

	List<Course> selectAll();

	void updateByPrimaryKey(Course record);

	PageResult query(CourseQueryObject qo);
}
