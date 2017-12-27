package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.DesignateHistory;
import cn.wolfcode.edu.domain.PotentialStudent;
import cn.wolfcode.edu.domain.StudentPool;
import cn.wolfcode.edu.mapper.StudentPoolMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.StudentPoolQueryObject;
import cn.wolfcode.edu.service.IDesignateHistoryService;
import cn.wolfcode.edu.service.IPotentialStudentService;
import cn.wolfcode.edu.service.IStudentPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentPoolServiceImpl implements IStudentPoolService{
	@Autowired
	private StudentPoolMapper mapper;
	@Autowired
	private IDesignateHistoryService designateHistoryService;
	@Autowired
	private IPotentialStudentService potentialStudentService;
	@Override
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(PotentialStudent record) {
		record.setTailnum(0);
		mapper.insert(record);
	}

	@Override
	public StudentPool selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<StudentPool> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(StudentPool record) {
		mapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(StudentPoolQueryObject qo) {
		int total = mapper.queryCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<StudentPool> rows = mapper.queryList(qo);
		return new PageResult(total,rows);
	}

	@Override
	public void changState(Long id) {
		mapper.changState(id);
	}
	@Override
	public void redo(DesignateHistory designateHistory){
		StudentPool sp = mapper.selectByPrimaryKey(designateHistory.getPoolId());
		sp.setSale(designateHistory.getTarget());
		potentialStudentService.insert((PotentialStudent) sp);
		mapper.changState(designateHistory.getPoolId());
		designateHistoryService.insert(designateHistory);
		mapper.deleteByPrimaryKey(designateHistory.getPoolId());
	}

}
