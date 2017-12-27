package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Course;
import cn.wolfcode.edu.mapper.CourseMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.CourseQueryObject;
import cn.wolfcode.edu.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{

	@Autowired
	private CourseMapper courseMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		courseMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Course record) {
		courseMapper.insert(record);
	}
	@Override
	public Course selectByPrimaryKey(Long id) {
		return courseMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Course> selectAll() {
		return courseMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Course record) {
		courseMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(CourseQueryObject qo) {
		int total = courseMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<Course> rows = courseMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

}
