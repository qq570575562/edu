package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.ClassGrade;
import cn.wolfcode.edu.mapper.ClassGradeMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IClassGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassGradeServiceImpl implements IClassGradeService{
	@Autowired
	private ClassGradeMapper classGradeMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		classGradeMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(ClassGrade record) {
		classGradeMapper.insert(record);
	}
	@Override
	public ClassGrade selectByPrimaryKey(Long id) {
		return classGradeMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<ClassGrade> selectAll() {
		return classGradeMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(ClassGrade record) {
		classGradeMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = classGradeMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<ClassGrade> rows = classGradeMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	@Override
	public List<ClassGrade> queryByClassroomId(Long id) {

		return classGradeMapper.queryByClassroomId(id);
	}

	@Override
	public void saveDis(Long classId, Long empId) {
		classGradeMapper.saveDis(classId,empId);
	}

	@Override
	public void openClass(Long id) {
		classGradeMapper.openClass(id);
	}

}
