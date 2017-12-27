package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Classroom;
import cn.wolfcode.edu.mapper.ClassroomMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassroomServiceImpl implements IClassroomService{

	@Autowired
	private ClassroomMapper classroomMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		classroomMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Classroom record) {
		classroomMapper.insert(record);
	}
	@Override
	public Classroom selectByPrimaryKey(Long id) {
		return classroomMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Classroom> selectAll() {
		return classroomMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Classroom record) {
		classroomMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = classroomMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<Classroom> rows = classroomMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	@Override
	public void changeState(Long id) {
		classroomMapper.changeState(id);
	}
}
