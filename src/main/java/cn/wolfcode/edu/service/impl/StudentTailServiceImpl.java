package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.StudentTail;
import cn.wolfcode.edu.mapper.PotentialStudentMapper;
import cn.wolfcode.edu.mapper.StudentTailMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.StudentTailQueryObject;
import cn.wolfcode.edu.service.IStudentTailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class StudentTailServiceImpl implements IStudentTailService {
	@Autowired
	private StudentTailMapper studentTailMapper;
	@Autowired
	private PotentialStudentMapper potentialStudentMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		studentTailMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(StudentTail record) {
		potentialStudentMapper.tailnum(record.getPid());
		studentTailMapper.insert(record);
	}
	@Override
	public StudentTail selectByPrimaryKey(Long id) {
		return studentTailMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<StudentTail> selectAll() {
		return studentTailMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(StudentTail record) {
		studentTailMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(StudentTailQueryObject qo) {
		int total = studentTailMapper.queryCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<StudentTail> rows = studentTailMapper.queryList(qo);
		return new PageResult(total,rows);
	}

	@Override
	public void changState(StudentTail studentTail) {
		studentTailMapper.changState(studentTail);
	}
}

